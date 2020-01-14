package com.iflytek.springboot.controller;

import com.iflytek.springboot.base.interfaces.Sy;
import com.iflytek.springboot.service.TempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TEMP控制层
 * @author shenyang
 * 2020年01月07日
 */
@Controller
@RequestMapping(value = "temp")
public class TempController  {
	@Autowired
	TempService tempService;
	/**
	 * 分页查询TEMP数据，返回json格式
	 * @return
	 */
	@PostMapping(value = "/tempList.do")
	@ResponseBody
	@Sy
	public Object tempListData() {
		return tempService.getFindAll();
	}

	/**
	 * 分页查询TEMP数据，返回json格式
	 * @return
	 */
	@PostMapping(value = "/add.do")
	@ResponseBody
	@Sy
	public Object add() {
		return tempService.add();
	}


}