/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年8月26日
 * @version: V1.0
 */

package com.config;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年8月26日
 */
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

/**
 * @Description: 继承DefaultWebSessionManager类,自定义getSessionId方法,以实现基于token登录
 * @author: Frankjiu
 * @date: 2020年8月26日
 */
public class SessionConfig extends DefaultWebSessionManager {

    public SessionConfig() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader("token");
        if (!StringUtils.isEmpty(id) && !"null".equals(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "Stateless request");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        } else {
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }
}