/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年8月26日
 * @version: V1.0
 */

package com.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.entity.User;

/**
 * @Description: Shiro Controller
 * @author: Frankjiu
 * @date: 2020年8月26日
 */
@RestController
@RequestMapping("/shiro")
public class ShiroController {

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    /**
     * 验证用户登陆信息
     */
    @PostMapping("/verificationUser")
    public String verificationUser(User user) {
        //验证用户信息
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //完成登录
            subject.login(usernamePasswordToken);
            return subject.getSession().getId().toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("shiroAll")
    @RequiresPermissions("shiro:all")
    @ResponseBody
    public Map<String, Object> shiroAll() {
        Subject subject = SecurityUtils.getSubject();
        String UserName = subject.getPrincipal().toString().split(":")[0];
        Map<String, Object> map = new HashMap<>();
        map.put("userName", UserName);
        map.put("value", "有权限");
        return map;
    }

    @GetMapping("noAuthority")
    @RequiresPermissions("noAuthority")
    @ResponseBody
    public Map<String, Object> noAuthority() {
        Subject subject = SecurityUtils.getSubject();
        String UserName = subject.getPrincipal().toString().split(":")[0];
        Map<String, Object> map = new HashMap<>();
        map.put("value", "无权限");
        map.put("userName", UserName);
        return map;
    }

    /**
     * 手动退出
     */
    @GetMapping("logout")
    public String logout(HttpServletRequest httpServletRequest) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "退出成功";
    }

    /**
     * 自动退出 退出后会重定向到跟目录
     */
    @GetMapping("logout2")
    public String logout2() {
        return "退出成功";
    }
}