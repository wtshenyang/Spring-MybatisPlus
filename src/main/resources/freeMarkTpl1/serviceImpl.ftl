package ${serviceImplPath};

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ${PageUtilPath};
import ${PageVoPath};
import ${ResultDOPath};
import ${SysCodePath};
import ${tableBeanPath}.${tableBeanName};

import ${package}.${tableName}Service;
import ${daoImplPath}.${tableName}Dao;
/**
 * ${tableDesc}服务接口
 * @author shenyang
 * ${dataTime}
 */
 @Service("${_tableName}Service")
public class ${tableName}ServiceImpl implements ${tableName}Service {
	@Autowired
	private ${tableName}Dao ${_tableName}Dao;

	/**
	 * 分页获取 ${tableDesc}列表数据 .
	 * @return ResultDO 分页数据
     * @author shenyang
	 */
    @Override
	public ResultDO<PageUtil<${tableBeanName}>> get${tableName}List(${tableBeanAllName} bean){
		//PageUtil<${tableBeanName}> pager = queryDO.getPageInfo();
	    PageUtil<${tableBeanName}> pager =new PageUtil<>();
		pager.setPageSize(bean.getPageSize());
		pager.setCurrentPage(bean.getPageNo());
		try{
		    //设置为false 则不分页
			//pager.setCount(false);
			pager.setDataList(${_tableName}Dao.listPage${tableBeanName}list(new PageVo<>(pager,bean)));
			return new ResultDO<PageUtil<${tableBeanName}>>(pager);

		}catch (Exception e){
			return new ResultDO<PageUtil<${tableBeanName}>>(SysCode.COM_ERROR,"查询内容列表出错");
		}
	}
	/**
	 * 根据主键查询 ${tableDesc}详细
	 * @param: key
	 * @return: ResultDO
	 */
    @Override
	public ResultDO<${tableBeanAllName}> get${tableName}By${tableKeyName}( ${tableKeyAppendName}){

       ${tableBeanAllName} bean = ${_tableName}Dao.selectByKey(${tableKeyLName});

		if(bean==null){
			return new ResultDO<${tableBeanAllName}>(SysCode.COM_ERROR, "信息不存在或已删除");
		}
		return new ResultDO<${tableBeanAllName}>(bean);
	}

	/**
	 * 新增 ${tableDesc}
	 * @param bean
	 * @return
	 */
    @Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT, rollbackFor = { Exception.class })
	public ResultDO<Boolean> insert${tableName}(${tableBeanAllName} bean){
		try{
           int i  = ${_tableName}Dao.insertSelective(bean);

		}catch(Exception e){
            e.printStackTrace();
			return new ResultDO<Boolean>(SysCode.COM_ERROR,"新增${tableDesc}失败");
		}
		return new ResultDO<Boolean>(true);
	}

	/**
	 * 修改 ${tableDesc}信息
	 * @param bean
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT, rollbackFor = { Exception.class })
	public ResultDO<Boolean> update${tableName}(${tableBeanAllName} bean){

		 try{

			int i  = ${_tableName}Dao.updateByKeySelective(bean);

		 }catch(Exception e){
			e.printStackTrace();
			return new ResultDO<Boolean>(SysCode.COM_ERROR,"编辑${tableDesc}失败");
	     }
		return new ResultDO<Boolean>(true);
	}
	/**
	 * 逻辑删除 ${tableDesc}
	 * @param map
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT, rollbackFor = { Exception.class })
	public ResultDO<Boolean> logicDelete${tableName}(${tableKeyAppendName}){
		 try{
		 	int i  = ${_tableName}Dao.deleteByKey(${tableKeyLName});

		}catch(Exception e){
            e.printStackTrace();
			return new ResultDO<Boolean>(SysCode.COM_ERROR,"删除${tableDesc}失败");
		}
		return new ResultDO<Boolean>(true);
	}
}
