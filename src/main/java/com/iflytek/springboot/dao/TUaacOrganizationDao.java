package com.iflytek.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iflytek.springboot.pojo.TUaacOrganizationBean;

import java.util.List;

/**
 * <p>操作表t_uaac_organization的dao层.
 * <p>包含方法：
 * <li>  根据数据库主键物理删除表t_uaac_organization记录:int deleteByKey(String id)
 * <li>  插入表t_uaac_organization记录，sql中没有空判断:int insert(TUaacOrganizationBean record)
 * <li>  插入表t_uaac_organization记录，sql中有空判断:int insertSelective(TUaacOrganizationBean record)
 * <li>  根据[String id]查询表t_uaac_organization记录:com.iflytek.springboot.pojo.TUaacOrganizationBean selectByKey(String id)
 * <li>  修改表t_uaac_organization记录，sql中有空判断:int updateByKeySelective(TUaacOrganizationBean record)
 * <li>  修改表t_uaac_organization记录，sql中没有空判断:int updateByKey(TUaacOrganizationBean record)
 * <li>  逻辑删除:int logicDelete(TUaacOrganizationBean bean)
 * <li>  分页查询:java.util.List<com.iflytek.springboot.pojo.TUaacOrganizationBean> listPageTUaacOrganizationBeanlist(PageVo<?> param)
 */
public interface TUaacOrganizationDao extends BaseMapper<TUaacOrganizationBean> {


    /**
     * 分页查询.
     * @return java.util.List<com.iflytek.springboot.pojo.TUaacOrganizationBean>
     */
    List<TUaacOrganizationBean> listPageTUaacOrganizationBeanlist(TUaacOrganizationBean param);


    List<TUaacOrganizationBean> getListAll(TUaacOrganizationBean param);
}