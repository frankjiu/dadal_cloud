/**
 * All rights Reserved, Designed By www.xcompany.com
 * 
 * @Package: com.config
 * @author: Frankjiu
 * @date: 2020年6月12日
 * @version: V1.0
 */

package com.config;

/**
 * @Description: ShiroRealm
 * @author: Frankjiu
 * @date: 2020年6月12日
 */
/*public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        User user = userService.queryUserByUsername(userName);
        if (user == null) {
            return null;
        }
        //这里会去校验密码是否正确
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), null,
                getName());
        return authenticationInfo;
    }

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

}*/