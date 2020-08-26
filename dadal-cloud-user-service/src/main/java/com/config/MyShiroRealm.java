/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年8月26日
 * @version: V1.0
 */

package com.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.UserDao;
import com.entity.User;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: realm域
 * @author: Frankjiu
 * @date: 2020年8月26日
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserDao userMapper;

    public MyShiroRealm() {
        super();
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        User userParam = User.builder().userName(userToken.getUsername()).password(new String(userToken.getPassword())).build();
        //根据账号密码查用户信息
        User user = userMapper.queryUser(userParam);
        if (null == user) {
            throw new AccountException("账号不存在！");
        } else if (!StringUtils.equals(user.getPassword(), userParam.getPassword())) {
            throw new AccountException("密码不正确！");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),
                getName()); //这里可以调用另外一个构造方法, 加盐加密处理
        return simpleAuthenticationInfo;
    }

    /**
     * 用户授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String userName = principalCollection.getPrimaryPrincipal().toString().split(":")[0];
        log.info(userName);
        //根据用户userName查询权限（permission) 此处省略sql写固定权限
        Set<String> permissions = new HashSet<>();
        permissions.add("shiro:all");
        info.setStringPermissions(permissions);
        return info;
    }

}