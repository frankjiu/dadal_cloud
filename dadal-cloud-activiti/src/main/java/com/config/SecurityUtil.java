/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年5月16日
 * @version: V1.0
 */
package com.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Description: SecurityUtil
 * @author: Frankjiu
 * @date: 2020年5月16日
 */
@Component
public class SecurityUtil {

	@Autowired
	private UserDetailsService userDetailsService;

	public void logInAs(String username) {

		UserDetails user = userDetailsService.loadUserByUsername(username);
		if (user == null) {
			throw new IllegalStateException("User " + username + " doesn't exist, please provide a valid user");
		}

		SecurityContextHolder.setContext(new SecurityContextImpl(new Authentication() {
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return user.getAuthorities();
			}

			@Override
			public Object getCredentials() {
				return user.getPassword();
			}

			@Override
			public Object getDetails() {
				return user;
			}

			@Override
			public Object getPrincipal() {
				return user;
			}

			@Override
			public boolean isAuthenticated() {
				return true;
			}

			@Override
			public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

			}

			@Override
			public String getName() {
				return user.getUsername();
			}
		}));
		org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(username);
	}
}
