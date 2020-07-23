/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.sysconfig   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月2日下午4:59:42
 * @version V1.0
 */

package com.modules.init;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Frankjiu
 * @date: 2020年4月2日 下午4:59:42
 */
@WebFilter("/*")
public class MyFilter implements Filter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info(">>>>>>>>>>>>filter init...");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		logger.info(">>>>>>>>>>>>filter do something...");
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		logger.info(">>>>>>>>>>>>filter destroy...");
	}

}
