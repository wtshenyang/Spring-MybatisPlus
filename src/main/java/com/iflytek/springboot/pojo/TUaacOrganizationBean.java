package com.iflytek.springboot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.iflytek.springboot.common.BaseBean;
import lombok.Data;

import java.io.Serializable;

/**
 * 表t_uaac_organization数据库Bean.
 */
@Data
@TableName("t_uaac_organization")
public class TUaacOrganizationBean extends BaseBean implements Serializable {
    /**
     * 机构ID.
     */
    private String id;

    /**
     * 机构编码.
     */
    private String code;

    /**
     * 机构名称.
     */
    private String name;

    /**
     * 名称简拼.
     */
    private String namePinyin;

    /**
     * 机构别名.
     */
    private String alias;

    /**
     * 机构简称.
     */
    private String shortName;

    /**
     * 简称简拼.
     */
    private String shortNamePinyin;

    /**
     * 机构类型.
     */
    private String type;

    /**
     * 上级机构.
     */
    private String uporgId;

    /**
     * 机构层级.
     */
    private Short orgLevel;

    /**
     * 联系人.
     */
    private String contacts;

    /**
     * 联系电话.
     */
    private String phone;

    /**
     * 邮箱.
     */
    private String email;

    /**
     * 地址.
     */
    private String address;

    /**
     * 邮编.
     */
    private String zipcode;

    /**
     * 机构描述.
     */
    private String description;

    /**
     * 备注.
     */
    private String remark;

    /**
     * 排序.
     */
    private Short orderNum;

    /**
     * 有效性.
     */
    private String validState;

    /**
     * 更新时间.
     */
    private String updateTime;

    /**
     * 删除状态.
     */
    private String deleteState;

    /**
     * 行政区划id.
     */
    private String xzqhId;

}