package com.iflytek.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 表temp数据库Bean.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempBean implements Serializable {
    /**
     * .
     */
    private Long id;

    /**
     * .
     */
    private String username;

    /**
     * .
     */
    private String password;


}