package com.springsecurity.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wongtx
 * @version 1.0
 * @date 2020/3/3 15:57
 */
@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -2856394355665216240L;
    private Integer userId;

    private Integer roleId;
}
