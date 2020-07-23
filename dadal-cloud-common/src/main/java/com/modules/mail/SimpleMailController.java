/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mail   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月12日下午12:50:00
 * @version V1.0
 */

package com.modules.mail;

import java.io.File;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.CommonApplication;
import com.modules.mail.constants.Constants.EmailConstants;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author: Frankjiu
 * @date: 2020年4月12日 下午12:50:00
 */
@Controller
public class SimpleMailController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static String emailTo = EmailConstants.EMAIL_TO;

	@Autowired
	MailService mailService;

	@Autowired
	TemplateEngine templateEngine;

	@Value("${spring.mail.username}")
	String emaiFrom;

	@GetMapping("sendSimpleMail")
	public String sendSimpleMail() {
		mailService.sendSimpleMail(emaiFrom, emailTo, emailTo, "subject", "content: meeting-notice of 2020-05-01");
		return "success";
	}

	@GetMapping("sendAttachFileMail")
	public String sendAttachFileMail() {
		mailService.sendAttachFileMail(emaiFrom, emailTo, "meeting file", "content: meeting-file of 2020-05-01",
				new File("D:\\backup\\会议纪要附件.doc"));
		return "success";
	}

	@GetMapping("sendImageMail")
	public String sendImageMail() {
		mailService.sendImageMail(emaiFrom, emailTo, "系统设计首页大图",
				"<div>图片邮件:" + "pic1: <div><img src='cid:p01'/></div>" + "pic2: <div><img src='cid:p02'/></div>" + "</div>",
				new String[] { "D:\\backup\\protect_photo1.jpg", "D:\\backup\\protect_photo2.jpg" }, new String[] { "p01", "p02" });
		return "success";
	}

	@GetMapping("sendHtmlMailByFreemarker")
	public String sendHtmlMailByFreemarker() {
		try {
			Configuration config = new Configuration(Configuration.VERSION_2_3_0);
			ClassLoader loader = CommonApplication.class.getClassLoader();
			config.setClassLoaderForTemplateLoading(loader, "ftl");
			Template template = config.getTemplate("mailtemplate.ftl");
			StringWriter writer = new StringWriter();
			EmailUser emailUser = new EmailUser();
			emailUser.setUsername("frank");
			emailUser.setGender("男");
			template.process(emailUser, writer);
			mailService.sendHtmlMail(emaiFrom, emailTo, "html mail", writer.toString());
			return "success";
		} catch (Exception e) {
			logger.info("send freemarker-html failed:{}", e.getMessage(), e);
			return "failed";
		}
	}

	@GetMapping("sendHtmlMailByThymeleaf")
	public String sendHtmlMailByThymeleaf() {
		try {
			Context thymeleafCtx = new Context();
			thymeleafCtx.setVariable("username", "frank");
			thymeleafCtx.setVariable("gender", "男");
			String mail = templateEngine.process("mailtemplate.html", thymeleafCtx);
			mailService.sendHtmlMail(emaiFrom, emailTo, "html mail", mail);
			return "success";
		} catch (Exception e) {
			logger.info("send thymeleaf-html failed:{}", e.getMessage(), e);
			return "failed";
		}
	}

}
