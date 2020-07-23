/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.websocket   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月8日上午8:42:09
 * @version V1.0
 */

package com.modules.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Message Handler Controller(send to all users)
 * 
 * @author: Frankjiu
 * @date: 2020年4月8日 上午8:42:09
 */
@Controller
public class MeetingController {

	private static final Logger logger = LoggerFactory.getLogger(MeetingController.class);

	@Autowired
	SimpMessagingTemplate msgTemplate;

	@MessageMapping("/meetings")
	// @SendTo("/topic/meetings")
	public void meetings(Message message) {
		try {
			msgTemplate.convertAndSend("/topic/meetings", message);
		} catch (Exception e) {
			logger.info("{}", e.getMessage(), e);
		}
	}

	/*
	 * @MessageMapping("/chatting") public void chatting(Principal principal,
	 * Message message) throws Exception { String from = principal.getName();
	 * message.setFrom(from); msgTemplate.convertAndSendToUser(message.getTo(),
	 * "/queue/chatting", message); }
	 */

}
