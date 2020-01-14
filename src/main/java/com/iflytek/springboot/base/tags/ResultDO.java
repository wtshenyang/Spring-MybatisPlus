package com.iflytek.springboot.base.tags;


import com.iflytek.springboot.base.utils.SysCode;

import java.io.Serializable;

/**
 * 业务操作响应Bean.
 *
 * @param <T>
 */
public class ResultDO<T> implements Serializable {
    private static final long serialVersionUID = -2042618546543630713L;
    // 返回值，可以是任何类型
    private T obj;
    // 响应错误码
    private String errorMsg;
    // 响应码
    private String code;

    public ResultDO() {
    }

    public ResultDO(T result) {
        this.code = SysCode.COM_SUCCESS;
        this.obj = result;
    }

    public ResultDO(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public ResultDO(String code, String errorMsg, T result) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.obj = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getObj() {
        return obj;
    }

    public ResultDO<T> setObj(T obj) {
        this.obj = obj;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public ResultDO<T> setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
