package com.iflytek.springboot.service.serviceImpl;

import com.iflytek.springboot.base.tags.PageUtil;
import com.iflytek.springboot.base.tags.PageVo;
import com.iflytek.springboot.base.tags.ResultDO;
import com.iflytek.springboot.base.utils.SysCode;
import com.iflytek.springboot.dao.TUaacOrganizationDao;
import com.iflytek.springboot.pojo.TUaacOrganizationBean;
import com.iflytek.springboot.service.TUaacOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * T_UAAC_ORGANIZATION服务接口
 *
 * @author shenyang
 * 2020年01月09日
 */
@Service("tUaacOrganizationService")
public class TUaacOrganizationServiceImpl implements TUaacOrganizationService {
    @Autowired
    private TUaacOrganizationDao tUaacOrganizationDao;

    /**
     * 分页获取 T_UAAC_ORGANIZATION列表数据 .
     *
     * @return ResultDO 分页数据
     * @author shenyang
     */
    @Override
    public ResultDO<PageUtil<TUaacOrganizationBean>> getTUaacOrganizationList(TUaacOrganizationBean bean) {
        List<TUaacOrganizationBean> tUaacOrganizationBeans = tUaacOrganizationDao.getListAll(null);
        List<TUaacOrganizationBean> tUaacOrganizationBeans2 = tUaacOrganizationDao.listPageTUaacOrganizationBeanlist(null);
        List<TUaacOrganizationBean> tUaacOrganizationBeans1 = tUaacOrganizationDao.selectList(null);
        return new ResultDO<PageUtil<TUaacOrganizationBean>>(null);

    }

}
