package com.springsecurity.demo.mapper;

import com.springsecurity.demo.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author wongtx
 * @version 1.0
 * @date 2020/3/3 16:01
 */
@Mapper
public interface SysUserMapper {
    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    SysUser selectById(Integer id);

    @Select("SELECT * FROM sys_user WHERE name = #{name}")
    SysUser selectByName(String name);

    @Insert("INSERT INTO sys_user(name,password) VALUES(#{name},#{password})")
    void insert(SysUser user);
}
