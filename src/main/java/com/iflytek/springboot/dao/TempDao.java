package com.iflytek.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iflytek.springboot.pojo.TUaacOrganizationBean;
import com.iflytek.springboot.pojo.TempBean;

/**
 * <p>操作表temp的dao层.
 * <p>包含方法：
 * <li>  根据数据库主键物理删除表temp记录:int deleteByKey(Short id)
 * <li>  插入表temp记录，sql中没有空判断:int insert(TempBean record)
 * <li>  插入表temp记录，sql中有空判断:int insertSelective(TempBean record)
 * <li>  根据[Short id]查询表temp记录:com.iflytek.springboot.pojo.TempBean selectByKey(Short id)
 * <li>  修改表temp记录，sql中有空判断:int updateByKeySelective(TempBean record)
 * <li>  修改表temp记录，sql中没有空判断:int updateByKey(TempBean record)
 * <li>  逻辑删除:int logicDelete(TempBean bean)
 * <li>  分页查询:java.util.List<com.iflytek.springboot.pojo.TempBean> listPageTempBeanlist(PageVo<?> param)
 */
public interface TempDao extends BaseMapper<TempBean> {

}