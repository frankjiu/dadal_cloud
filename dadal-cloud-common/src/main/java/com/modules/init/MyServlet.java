/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.sysconfig   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月2日下午4:56:45
 * @version V1.0
 */

package com.modules.init;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Frankjiu
 * @date: 2020年4月2日 下午4:56:45
 */
@WebServlet("/my")
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		logger.info(">>>>>>>>>>>>name:{}", req.getParameter("name"));

	}

}
