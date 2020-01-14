package com.iflytek.springboot.service;

import com.iflytek.springboot.base.tags.PageUtil;
import com.iflytek.springboot.base.tags.PageVo;
import com.iflytek.springboot.base.tags.ResultDO;
import com.iflytek.springboot.pojo.TempBean;

import java.util.List;

/**
 * TEMP服务接口1
 * @author shenyang
 * 2020年01月07日
 */
public interface TempService {

	List<TempBean> getFindAll();

	int add();
}
