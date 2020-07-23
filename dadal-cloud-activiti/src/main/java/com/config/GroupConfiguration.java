/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年5月16日
 * @version: V1.0
 */
package com.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Description: Group Configuration
 * @author: Frankjiu
 * @date: 2020年5月16日
 */
@Configuration
@EnableWebSecurity
public class GroupConfiguration extends WebSecurityConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(GroupConfiguration.class);

	private static final String PASS_WORD = "password";
	private static final String ROLE_ACTIVITI_USER = "ROLE_ACTIVITI_USER";
	// 注意: GROUP_activitiTeam: activiti规范是以 'GROUP_'开始, 实际组名为 'activitiTeam'
	private static final String GROUP_ACTIVITITEAM = "GROUP_activitiTeam";

	@Override
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService service = myUserDetailsService();
		auth.userDetailsService(service);
	}

	@Bean
	public UserDetailsService myUserDetailsService() {
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
		String[][] usersGroupsAndRoles = { { "salaboy", PASS_WORD, ROLE_ACTIVITI_USER, GROUP_ACTIVITITEAM },
				{ "ryandawsonuk", PASS_WORD, ROLE_ACTIVITI_USER, GROUP_ACTIVITITEAM },
				{ "erdemedeiros", PASS_WORD, ROLE_ACTIVITI_USER, GROUP_ACTIVITITEAM },
				{ "other", PASS_WORD, ROLE_ACTIVITI_USER, "GROUP_otherTeam" }, { "admin", PASS_WORD, "ROLE_ACTIVITI_ADMIN" }, };
		for (String[] user : usersGroupsAndRoles) {
			List<String> authoritiesStrings = Arrays.asList(Arrays.copyOfRange(user, 2, user.length));
			logger.info(">>>>>> Registering new user:{} with the following Authorities[{}]", user[0], authoritiesStrings);
			inMemoryUserDetailsManager.createUser(new User(user[0], passwordEncoder().encode(user[1]),
					authoritiesStrings.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList())));
		}
		return inMemoryUserDetailsManager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
