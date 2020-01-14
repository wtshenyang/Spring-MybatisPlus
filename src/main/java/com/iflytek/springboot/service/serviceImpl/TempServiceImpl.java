package com.iflytek.springboot.service.serviceImpl;

import com.iflytek.springboot.dao.TempDao;
import com.iflytek.springboot.pojo.TempBean;
import com.iflytek.springboot.service.TempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * TEMP服务接口
 * @author shenyang
 * 2020年01月07日
 */
 @Service("tempService")
public class TempServiceImpl implements TempService {
	@Autowired
	private TempDao tempDao;


	@Override
	public List<TempBean> getFindAll() {
		//List<TempBean> list = tempDao.selectList(null);
		/*List<TempBean> list = tempDao.selectList(null);
		for (TempBean tempBean : list) {
			System.out.println(tempBean);
		}
		return list;*/
		return null;
	}

	@Override
	public int add() {
		TempBean bean =new TempBean();
		bean.setUsername("测试");
		bean.setPassword("密码");
	/*	bean.setId(1L);*/
		//int insert = tempDao.insert(bean);
		System.out.println(bean.getId());
		return 1;
	}
}
