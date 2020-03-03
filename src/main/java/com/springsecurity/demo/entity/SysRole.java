package com.springsecurity.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wongtx
 * @version 1.0
 * @date 2020/3/3 15:57
 */
@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = -8123368706218566583L;
    private Integer id;

    private String name;
}
