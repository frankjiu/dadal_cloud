/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com   
 * @author: Frankjiu
 * @date: 2020年5月2日
 * @version: V1.0
 */

package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.controller.MsgSender;

/**
 * @Description: Message Queue Send
 * @author: Frankjiu
 * @date: 2020年5月2日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQOperatorApplication.class)
public class QueueMsg {

	@Autowired
	private MsgSender msgSender;

	@Test
	public void sendMsg() throws InterruptedException {
		Thread.sleep(1000);
		this.msgSender.send(">>>MsgSender had sent the message>>>");
	}

}
