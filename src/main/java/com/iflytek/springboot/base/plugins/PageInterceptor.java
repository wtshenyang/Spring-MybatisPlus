package com.iflytek.springboot.base.plugins;

import com.iflytek.springboot.base.tags.PageUtil;
import com.iflytek.springboot.base.tags.PageVo;
import com.iflytek.springboot.base.utils.ValidateUtil;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


/**
 * 判断如果参数中有 {@link PageUtil} 对象，那么执行分页查询。(1.查询总数并放入page对象中。
 * 2.构造带有limit子句的sql替换原始的sql)
 * 目前只支持把page放到HashMap中(或使用接口时，把page作为方法的参数),并且key为"page" .
 *  高版本的 mybites 需要加Integer.class
 * @author liuqing
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class})})
@SuppressWarnings("rawtypes")
public class PageInterceptor implements Interceptor {
    public static final String PAGE_KEY = "page";
    protected String dialect = "oracle";
    protected String pageSqlId = ".*listPage.*"; // mapper.xml中需要拦截的ID(正则匹配)

    /**
     * <p>分特拦截器 .</p>
     *
     * @param ivk 参数
     * @return 拦截对象
     * @throws Throwable 异常
     * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
     */
    @Override
    public final Object intercept(Invocation ivk) throws Throwable {
        if (ivk.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
            BaseStatementHandler handler = (BaseStatementHandler) metaStatementHandler.getValue("delegate");
            MappedStatement ms = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
//			BaseStatementHandler handler = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler,
//					"delegate");
//			MappedStatement ms = (MappedStatement) ReflectHelper.getValueByFieldName(handler, "mappedStatement");
            if (ms.getId().matches(pageSqlId)) { // 拦截需要分页的SQL
                BoundSql bs = handler.getBoundSql();
                Object param = bs.getParameterObject();
                String sql = bs.getSql();
                sql = dataShiro(ms, sql, bs);
                if (param instanceof HashMap) {
                    HashMap map = (HashMap) param;
                    PageUtil p = (PageUtil) map.get(PAGE_KEY);
                    if (p != null) {
                        if (p.isCount()) {
                            p.setTotal(queryTotal(ivk, ms, bs, param, sql));
                            if (p.getPageSize() <= 0) {
                                sql = "select 1 from dual where 1=2";
                                while (bs.getParameterMappings().size() > 0) {
                                    bs.getParameterMappings().remove(0);
                                }
                            }
                        }
                        BoundSql newbs = new BoundSql(ms.getConfiguration(), pageSql(sql, p), bs.getParameterMappings(), param);
                        metaStatementHandler.setValue("delegate.boundSql", newbs);
//						ReflectHelper.setValueByFieldName(bs, "sql", pageSql(sql, p));
                    }
                } else if (param instanceof PageVo) {
                    PageVo map = (PageVo) param;
                    PageUtil p = (PageUtil) map.getPageInfo();
                    if (p != null) {
                        if (p.isCount()) {
                            p.setTotal(queryTotal(ivk, ms, bs, param, sql));
                            if (p.getPageSize() <= 0) {
                                sql = "select 1 from dual where 1=2";
                                while (bs.getParameterMappings().size() > 0) {
                                    bs.getParameterMappings().remove(0);
                                }
                            }
                        }
                        if (p.isCount()) {
                            BoundSql newbs = new BoundSql(ms.getConfiguration(), pageSql(sql, p), bs.getParameterMappings(), param);
                            metaStatementHandler.setValue("delegate.boundSql", newbs);
                        }else{
                            BoundSql newbs = new BoundSql(ms.getConfiguration(), sql, bs.getParameterMappings(), param);
                            metaStatementHandler.setValue("delegate.boundSql", newbs);
                        }

                    }
                }// 分页查询不传入分页信息，查询所有
                else {
                    BoundSql newbs = new BoundSql(ms.getConfiguration(), sql, bs.getParameterMappings(), param);
                    metaStatementHandler.setValue("delegate.boundSql", newbs);
                }
            }
        }
        Object obj = ivk.proceed();
        return obj;//ivk.proceed();
    }

    /**
     * 为count语句设置参数 .
     *
     * @param ps              参数1
     * @param ms              参数2
     * @param bs              参数3
     * @param parameterObject 参数4
     * @throws SQLException sql异常
     * @see org.apache.ibatis.executor.parameter.DefaultParameterHandler#setParameters(PreparedStatement)
     */
    @SuppressWarnings("unchecked")
    protected void setParameters(PreparedStatement ps, MappedStatement ms, BoundSql bs, Object parameterObject) throws SQLException {
//		new DefaultParameterHandler(ms,parameterObject,bs).setParameters(ps);

        ErrorContext.instance().activity("setting parameters").object(ms.getParameterMap().getId());
        List<ParameterMapping> mappings = bs.getParameterMappings();
        if (mappings == null) {
            return;
        }
        Configuration configuration = ms.getConfiguration();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
        for (int i = 0; i < mappings.size(); i++) {
            ParameterMapping parameterMapping = mappings.get(i);
            if (parameterMapping.getMode() != ParameterMode.OUT) {

                Object value;
                String propertyName = parameterMapping.getProperty();
                PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                if (parameterObject == null) {
                    value = null;
                } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    value = parameterObject;
                } else if (bs.hasAdditionalParameter(propertyName)) {
                    value = bs.getAdditionalParameter(propertyName);
                } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && bs.hasAdditionalParameter(prop.getName())) {
                    value = bs.getAdditionalParameter(prop.getName());
                    if (value != null) {
                        value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                    }
                } else {
                    value = metaObject == null ? null : metaObject.getValue(propertyName);
                }
                if (null != value && value.getClass().toString().indexOf("String") != -1) {
                    value = value.toString().replace("%", "\\%").replace("'", "\\'").replace("\"", "\\\"");
                    metaObject.setValue(propertyName, value);
                }
                TypeHandler typeHandler = parameterMapping.getTypeHandler();
                if (typeHandler == null) {
                    throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + ms.getId());
                }
                typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
            }
        }
    }

    /**
     * 生成特定数据库的分页语句.
     *
     * @param sql  sql语句
     * @param page 分页bean
     * @return sql字符串
     */
    protected String pageSql(String sql, PageUtil page) {
        if (page == null || page.getDataSource() == null || "".equals(page.getDataSource())) {
            return sql;
        }
        StringBuilder sb = new StringBuilder();
        if ("oracle".equals(page.getDataSource())) {
            sb.append("select * from (select tmp_tb.*,(ROWNUM) row_id from (");

            if (ValidateUtil.isNotEmpty(page.getSortName())) {
                if (sql.contains("ORDER BY")) {
                    String send = sql.toUpperCase().substring(sql.lastIndexOf("ORDER BY"), sql.length());
                    if (!send.contains(" WHERE ")) {
                        sb.append(sql.substring(0, sql.lastIndexOf("ORDER BY")));
                    } else {
                        sb.append(sql);
                    }
                } else {
                    sb.append(sql);
                }
                sb.append(" order by ").append(page.getSortName());
                if (ValidateUtil.isNotEmpty(page.getSortOrder())) {
                    sb.append(" ").append(page.getSortOrder());
                }
            } else {
                sb.append(sql);
            }
            sb.append(")  tmp_tb where (ROWNUM)<=");
            sb.append(page.getCurrentResult() + page.getPageSize());
            sb.append(") where (row_id)>");
            sb.append(page.getCurrentResult());
        } else if ("mysql".equals(page.getDataSource())) {
            sb.append(sql);
            sb.append(" limit ");
            sb.append(page.getCurrentResult());
            sb.append(",");
            sb.append(page.getPageSize());
        } else if ("gp".equals(page.getDataSource())) {
            sb.append(sql);
            sb.append(" limit ");
            sb.append(page.getPageSize());
            sb.append(" OFFSET ");
            sb.append((page.getCurrentResult()));
        } else {
            throw new IllegalArgumentException("分页插件不支持此数据库：" + page.getDataSource());
        }
        return sb.toString();
    }

    /**
     * <p>插件.</p>
     *
     * @param arg0 参数1
     * @return object
     * @see org.apache.ibatis.plugin.Interceptor#plugin(Object)
     */
    @Override
    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    public String dataShiro(MappedStatement ms, String sql, BoundSql param) {
        return sql;
    }

    /**
     * <p>设置配置文件.</p>
     *
     * @param p 配置文件
     * @see org.apache.ibatis.plugin.Interceptor#plugin(Object)
     */
    @Override
    public void setProperties(Properties p) {
        dialect = p.getProperty("dialect");
    }

    /**
     * 查询总数 .
     *
     * @param ivk      拦截器
     * @param ms       mapping对象
     * @param boundSql boundSql对象
     * @param param    参数
     * @param sql      sql语句
     * @return 查询的总数
     * @throws SQLException 异常
     */
    public int queryTotal(Invocation ivk, MappedStatement ms, BoundSql boundSql, Object param, String sql)
            throws SQLException {
        Connection conn = (Connection) ivk.getArgs()[0];
        String countSql = "select count(1) from (" + sql + ") tmp_count";

        BoundSql bs = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), param);
        MetaObject metabs = SystemMetaObject.forObject(boundSql);
        MetaObject metaParamsField = (MetaObject) metabs.getValue("metaParameters");
        if (metaParamsField != null) {
            try {
//				mo = (MetaObject) ReflectHelper.getValueByFieldName(boundSql, "metaParameters");
                SystemMetaObject.forObject(bs).setValue("metaParameters", metaParamsField);
//				ReflectHelper.setValueByFieldName(bs, "metaParameters", metaParamsField);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ResultSet rs = null;
        PreparedStatement stmt = null;
        int count = 0;
        try {
            stmt = conn.prepareStatement(countSql);
            setParameters(stmt, ms, bs, param);
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
                stmt.close();
            }
        }
        return count;
    }

    /**
     * <p>获取字典.</p>
     *
     * @throws
     * @param: @return  字典
     * @return: String
     */
    public String getDialect() {
        return dialect;
    }

    /**
     * <p>设置字典.</p>
     *
     * @param _dialect 字典名称
     */
    public void setDialect(String _dialect) {
        this.dialect = _dialect;
    }

    /**
     * <p>获取sql.</p>
     *
     * @param: @return  sql
     * @return: String
     */
    public String getPageSqlId() {
        return pageSqlId;
    }

    /**
     * <p>设置sql.</p>
     *
     * @param _pageSqlId sql语句
     */
    public void setPageSqlId(String _pageSqlId) {
        this.pageSqlId = _pageSqlId;
    }
}
