package com.springsecurity.demo.service;

import com.springsecurity.demo.entity.SysUser;
import com.springsecurity.demo.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wongtx
 * @version 1.0
 * @date 2020/3/3 16:04
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }

    public void insert(SysUser user) {
        userMapper.insert(user);
    }
}
