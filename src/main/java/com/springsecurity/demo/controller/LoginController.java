package com.springsecurity.demo.controller;

import com.springsecurity.demo.common.Msg;
import com.springsecurity.demo.entity.SysRole;
import com.springsecurity.demo.entity.SysUser;
import com.springsecurity.demo.entity.SysUserRole;
import com.springsecurity.demo.service.SysRoleService;
import com.springsecurity.demo.service.SysUserRoleService;
import com.springsecurity.demo.service.SysUserService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wongtx
 * @version 1.0
 * @date 2020/3/3 16:03
 */
@Controller
public class LoginController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Autowired
    private SysRoleService roleService;

    @RequestMapping("/")
    public String showHome() {
        return "home.html";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login.html";
    }


    @RequestMapping("/login/error")
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        AuthenticationException exception =
                (AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        try {
            response.getWriter().write(exception.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register.html";
    }

    @PostMapping("/register")
    public Object register(SysUser user, Integer[] roles) throws Exception {
        String name = user.getName();

        if (StringUtils.isBlank(name) || StringUtils.isBlank(user.getPassword())) {
            throw new Exception("输入数据错误");
        }

        if (userService.selectByName(name) != null) {
            throw new Exception("用户名已被注册");
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.insert(user);
        int id = userService.selectByName(name).getId();
        for (Integer roleId : roles) {
            userRoleService.insert(id, roleId);
        }

        return "redirect:/login";
    }

    @GetMapping("/userInfo")
    @ResponseBody
    public Msg<?> getSelfUserInfo() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        SysUser user = userService.selectByName(name);
        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());
        List<SysRole> roles = new ArrayList<>();
        for (SysUserRole userRole : userRoles) {
            SysRole role = roleService.selectById(userRole.getRoleId());
            roles.add(role);
        }

        Map<String, Object> map = new HashMap<>(16);
        map.put("name", name);
        map.put("roles", roles);

        return new Msg<>(true, null, map);
    }

    @RequestMapping("/login/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String invalid() {
        return "Session 已过期，请重新登录";
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin','r')")
    public String printAdminR() {
        return "如果你看见这句话，说明你访问/admin路径具有r权限";
    }

    @RequestMapping("/admin/c")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin','c')")
    public String printAdminC() {
        return "如果你看见这句话，说明你访问/admin路径具有c权限";
    }

}
