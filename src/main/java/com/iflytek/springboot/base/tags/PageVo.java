package com.iflytek.springboot.base.tags;

import java.io.Serializable;

public class PageVo<T> implements Serializable {
    private PageUtil<?> pageInfo;
    private T Param;
    private Object otherParam;

    public PageVo(PageUtil<?> page, T param) {
        this.pageInfo = page;
        this.Param = param;
    }

    public PageVo(T param) {
        this.pageInfo = new PageUtil();
        ;
        this.Param = param;
    }

    public Object getOtherParam() {
        return otherParam;
    }

    public void setOtherParam(Object otherParam) {
        this.otherParam = otherParam;
    }

    public <S> PageUtil<S> getPageInfo() {
        return (PageUtil<S>) pageInfo;
    }

    public void setPageInfo(PageUtil<?> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public T getParam() {
        return Param;
    }

    public void setParam(T param) {
        Param = param;
    }


}
