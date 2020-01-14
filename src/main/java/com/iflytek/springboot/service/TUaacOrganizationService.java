package com.iflytek.springboot.service;

import com.iflytek.springboot.base.tags.PageUtil;
import com.iflytek.springboot.base.tags.PageVo;
import com.iflytek.springboot.base.tags.ResultDO;
import com.iflytek.springboot.pojo.TUaacOrganizationBean;

/**
 * T_UAAC_ORGANIZATION服务接口1
 * @author shenyang
 * 2020年01月09日
 */
public interface TUaacOrganizationService {


	/**
	 * 分页获取 T_UAAC_ORGANIZATION列表数据 .
	 * @param queryDO  分页参数以及检索参数
	 * @return ResultDO 分页数据
	 */
	public ResultDO<PageUtil<TUaacOrganizationBean>> getTUaacOrganizationList(TUaacOrganizationBean bean);


}
