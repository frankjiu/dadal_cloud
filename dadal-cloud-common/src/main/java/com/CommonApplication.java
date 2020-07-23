/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com   
 * @author: Frankjiu
 * @date: 2020年5月5日
 * @version: V1.0
 */

package com;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.constants.Constants;
import com.modules.init.InitController;
import com.utils.SpringUtil;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: CommonApplication
 * @author: Frankjiu
 * @date: 2020年5月5日
 */
@EnableCaching //在需要缓存的业务上加 @Cacheable(value = "common") 即可支持缓存逻辑
@EnableScheduling
//@MapperScan("com.mapper")
@EnableBatchProcessing
@EnableJms
@EnableSwagger2
@Slf4j
@SpringCloudApplication
public class CommonApplication {

	@Autowired
	InitController initController;

	@Value("${ActiveMQ_URL}")
	private String ActiveMQ_URL;

	@Value("${ActiveMQ_USER}")
	private String ActiveMQ_USER;

	@Value("${ActiveMQ_PASSWORD}")
	private String ActiveMQ_PASSWORD;

	@Value("${spring.jms.pub-sub-domain}")
	private boolean pubSubDomain;

	@Value("${Activemq.Priority}")
	private Integer priority;

	@Value("${Activemq.TimeToLive}")
	private Integer timeToLive;

	@Value("${spring.data.solr.host}")
	private String solrHost;

	/**
	 * 消息重发机制
	 * 
	 * @return
	 */
	@Bean
	public RedeliveryPolicy redeliveryPolicy() {
		RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
		// 是否在每次尝试重新发送失败后,延长该等待时间
		redeliveryPolicy.setUseExponentialBackOff(true);
		// 重发次数,默认为6次 这里设置为10次
		redeliveryPolicy.setMaximumRedeliveries(10);
		// 重发时间间隔,默认为1秒
		redeliveryPolicy.setInitialRedeliveryDelay(1);
		// 第一次失败后重新发送之前等待500毫秒,第二次失败再等待500*2毫秒,这里的2就是value
		redeliveryPolicy.setBackOffMultiplier(2);
		// 是否避免消息碰撞
		redeliveryPolicy.setUseCollisionAvoidance(false);
		// 设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
		redeliveryPolicy.setMaximumRedeliveryDelay(-1);
		return redeliveryPolicy;
	}

	/**
	 * 初始化JMS连接工厂: 默认工厂broker的IP一直指向localhost
	 */
	/*@Bean
	ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory();
	}*/

	// 初始化JMS连接工厂: 手动配置
	@Bean
	public ConnectionFactory connectionFactory(RedeliveryPolicy redeliveryPolicy) {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(ActiveMQ_URL);
		connectionFactory.setUserName(ActiveMQ_USER);
		connectionFactory.setPassword(ActiveMQ_PASSWORD);
		//设置重发机制
		//connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
		return connectionFactory;
	}

	/**
	 * 消息持久化
	 * 
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		// 为将要发送的消息设置优先级:10个优先级.0-4为一般优先级,5-9为加速优先级
		jmsTemplate.setPriority(priority);
		// 获取消息的存活时间毫秒单位
		jmsTemplate.setTimeToLive(timeToLive);
		//设置是否持久化要发送的消息：1-非持久化；2-持久化
		//jmsTemplate.setDeliveryMode(1);
		//设置是否持久化要发送的消息，true-持久化；false-非持久化
		//jmsTemplate.setDeliveryPersistent(true);
		return jmsTemplate;
	}

	@Bean(value = "jmsMessagingTemplate")
	JmsMessagingTemplate jmsMessagingTemplate(JmsTemplate jmsTemplate) {
		JmsMessagingTemplate messagingTemplate = new JmsMessagingTemplate(jmsTemplate);
		return messagingTemplate;
	}

	// queue模式的ListenerContainer
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		bean.setConnectionFactory(activeMQConnectionFactory);
		//设置连接数
		//bean.setConcurrency("4-10");
		//设置重连时间
		//bean.setRecoveryInterval(2000L);

		//ActiveMQ的消息确认机制即ack机制:
		/*AUTO_ACKNOWLEDGE = 1    自动确认
		CLIENT_ACKNOWLEDGE = 2    客户端手动确认   
		DUPS_OK_ACKNOWLEDGE = 3    自动批量确认
		SESSION_TRANSACTED = 0    事务提交并确认
		INDIVIDUAL_ACKNOWLEDGE = 4    单条消息确认 activemq 独有*/
		//消息应答模式
		//bean.setSessionAcknowledgeMode(1);
		return bean;
	}

	// topic模式的ListenerContainer
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		bean.setPubSubDomain(pubSubDomain);
		bean.setConnectionFactory(activeMQConnectionFactory);
		return bean;
	}

	// 初始化一个队列
	@Bean
	public Destination destination() {
		return new ActiveMQQueue(Constants.QUEUE_DESTINATION);
	}

	// 初始化一个主题
	@Bean
	public Topic topic() {
		return new ActiveMQTopic(Constants.TOPIC_NAME);
	}

	/**
	 * 初始化solr客户端
	 */
	@Bean
	public SolrClient solrClient() {
		return new HttpSolrClient.Builder(solrHost).build();
	}

	// 配置SolrTemplate
	@Bean
	public SolrTemplate solrTemplate(SolrClient solrClient) throws Exception {
		return new SolrTemplate(solrClient);
	}

	/**
	 * 引导启动入口
	 */
	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
		InitController initCtl = (InitController) SpringUtil.getBean("initController");
		String initInfo = initCtl.init();
		log.info("读取配置文件信息:{}", initInfo);
	}

}
