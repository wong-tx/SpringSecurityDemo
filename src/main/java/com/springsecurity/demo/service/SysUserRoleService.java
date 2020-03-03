package com.springsecurity.demo.service;

import com.springsecurity.demo.entity.SysRole;
import com.springsecurity.demo.entity.SysUserRole;
import com.springsecurity.demo.mapper.SysRoleMapper;
import com.springsecurity.demo.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wongtx
 * @version 1.0
 * @date 2020/3/3 16:05
 */
@Service
public class SysUserRoleService {
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    public List<SysUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }

    public void insert(Integer userId, Integer roleId) {
        userRoleMapper.insert(userId, roleId);
    }
}
