package com.springsecurity.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wongtx
 * @version 1.0
 * @date 2020/3/3 15:51
 */
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = -8623621183987411810L;
    private Integer id;

    private String name;

    private String password;
}
