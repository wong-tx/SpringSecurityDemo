package com.springsecurity.demo.service;

import com.springsecurity.demo.entity.SysPermission;
import com.springsecurity.demo.mapper.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wongtx
 * @version 1.0
 * @date 2020/3/4 10:32
 */
@Service
public class SysPermissionService {
    @Autowired
    private SysPermissionMapper permissionMapper;

    /**
     * 获取指定角色所有权限
     */
    public List<SysPermission> listByRoleId(Integer roleId) {
        return permissionMapper.listByRoleId(roleId);
    }

}
