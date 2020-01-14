package com.iflytek.springboot.common;

import com.baomidou.mybatisplus.annotation.TableField;

public class BaseBean {

    @TableField(exist = false)
    private int pageSize = 10;

    @TableField(exist = false)
    private int pageNo = 0;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
