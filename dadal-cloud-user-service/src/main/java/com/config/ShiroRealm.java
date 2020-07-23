/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年6月12日
 * @version: V1.0
 */

package com.config;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.entity.User;
import com.service.PermissionService;
import com.service.RoleService;
import com.service.UserService;

/**
 * @Description: ShiroRealm
 * @author: Frankjiu
 * @date: 2020年6月12日
 */
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String username = (String) principalCollection.getPrimaryPrincipal();
		// 查询当前用户的权限信息
		Set<String> roles = roleService.queryAllRolenameByUsername(username);
		Set<String> perms = permissionService.queryAllPermissionByUsername(username);
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
		simpleAuthorizationInfo.setStringPermissions(perms);

		return simpleAuthorizationInfo;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String usernmae = (String) authenticationToken.getPrincipal();
		User user = userService.queryUserByUsername(usernmae);
		if (user == null) {
			return null;
		}
		//这里会去校验密码是否正确
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), null,
				getName());
		return authenticationInfo;
	}
}