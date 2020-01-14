package com.iflytek.springboot.controller;

import com.iflytek.springboot.base.interfaces.Sy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.iflytek.springboot.base.tags.PageUtil;
import com.iflytek.springboot.base.tags.PageVo;
import com.iflytek.springboot.base.tags.ResultDO;
import com.iflytek.springboot.pojo.TUaacOrganizationBean;
import com.iflytek.springboot.service.TUaacOrganizationService;

/**
 * T_UAAC_ORGANIZATION控制层
 * @author shenyang
 * 2020年01月09日
 */
@Controller
@RequestMapping(value = "tUaacOrganization")
public class TUaacOrganizationController  {
	@Autowired
	TUaacOrganizationService tUaacOrganizationService;
	/**
	 * 分页查询T_UAAC_ORGANIZATION数据，返回json格式
	 * @param bean
	 * @return
	 */
	@PostMapping(value = "/tUaacOrganizationList.do")
	@ResponseBody
	@Sy
	public  ResultDO<?> tUaacOrganizationListData(@RequestBody TUaacOrganizationBean bean) {
		return tUaacOrganizationService.getTUaacOrganizationList(bean);
	}



}