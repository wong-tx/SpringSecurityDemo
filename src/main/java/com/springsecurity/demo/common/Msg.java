package com.springsecurity.demo.common;

import lombok.Data;

/**
 * @author wongtx
 * @version 1.0
 * @date 2020/3/3 16:11
 */
@Data
public class Msg<T> {
    private Boolean status;

    private String info;

    private T data;

    public Msg() {

    }

    public Msg(Boolean status, String info, T data) {
        this.status = status;
        this.info = info;
        this.data = data;
    }
}
