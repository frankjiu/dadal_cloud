/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mail   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月12日下午12:43:45
 * @version V1.0
 */

package com.modules.mail;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Send Simple Mail
 * 
 * @author: Frankjiu
 * @date: 2020年4月12日 下午12:43:45
 */
@Component
public class MailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	JavaMailSender javaMailSender;

	public void sendSimpleMail(String from, String to, String cc, String subject, String content) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(from);
			msg.setTo(to);
			msg.setCc(cc);
			msg.setSubject(subject);
			msg.setText(content);
			javaMailSender.send(msg);
		} catch (Exception e) {
			logger.info("Send Simple Mail Failed: {}", e.getMessage(), e);
		}

	}

	public void sendAttachFileMail(String from, String to, String subject, String content, File file) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage, true);
			msgHelper.setFrom(from);
			msgHelper.setTo(to);
			msgHelper.setSubject(subject);
			msgHelper.setText(content);
			msgHelper.addAttachment(file.getName(), file);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			logger.info("Send AttachFile Mail Failed: {}", e.getMessage(), e);
		}
	}

	public void sendImageMail(String from, String to, String subject, String content, String[] imgPaths, String[] ids) {
		if (imgPaths.length != ids.length) {
			logger.info("send image failed!");
			return;
		}
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage, true);
			msgHelper.setFrom(from);
			msgHelper.setTo(to);
			msgHelper.setSubject(subject);
			msgHelper.setText(content, true);
			for (int i = 0; i < imgPaths.length; i++) {
				FileSystemResource fileResource = new FileSystemResource(new File(imgPaths[i]));
				msgHelper.addInline(ids[i], fileResource);
			}
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			logger.info("Send Image Mail Failed: {}", e.getMessage(), e);
		}
	}

	public void sendHtmlMail(String from, String to, String subject, String content) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage, true);
			msgHelper.setFrom(from);
			msgHelper.setTo(to);
			msgHelper.setSubject(subject);
			msgHelper.setText(content, true);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			logger.info("Send HTML Mail Failed: {}", e.getMessage(), e);
		}
	}

}
