一.表结构设计与创建表脚本:
CREATE TABLE SYS_USER(
ID VARCHAR(32) 									COMMENT '主键'                                   ,
USER_NAME VARCHAR(30)							COMMENT '姓名'                                   ,
LOGIN_NAME VARCHAR(50)							COMMENT '账号'                                   ,
 
PASS_WORD VARCHAR(32)							COMMENT '密码'									 ,				 
MOBILE_PHONE VARCHAR(11)    					COMMENT '手机号码'						         ,
WECHAT VARCHAR(50)          					COMMENT '微信号' 								 ,

ID_CARD VARCHAR(18)         					COMMENT '身份证号' 							     ,
EMAIL VARCHAR(50)           					COMMENT '电子邮箱' 							     ,

TYPE INT(1) DEFAULT 2 							COMMENT '用户角色类型 0:管理员/1:专家/2:普通/3:黑名单',
IS_FREEZE INT(1)            					COMMENT '是否冻结 0:否/1:是'   				 	 ,

SUBJECT_ID VARCHAR(32)              			COMMENT '专业学科ID'  						     ,
HAS_UPDATED BOOLEAN DEFAULT FALSE				COMMENT '是否已修改过学科'  					 ,
POINTS INT(11)              					COMMENT '用户积分'                               ,

GRADE VARCHAR(6) 	        					COMMENT '用户级别称谓,根据积分判断'              ,
COIN_RATIO INT(1)           					COMMENT '金币奖励系数 普通用户:1/专家用户:2'	 ,
LOGIN_TIME DATETIME         					COMMENT '最近登录时间' 						     ,

LOGOUT_TIME DATETIME        					COMMENT '最近退出时间'                           ,
CREATE_TIME DATETIME        					COMMENT '创建时间'                               ,
UPDATE_TIME DATETIME    						COMMENT '更新时间'                               ,

PRIMARY KEY (ID)
) ENGINE=INNODB CHARSET=UTF8 COMMENT='用户表';



CREATE TABLE SYS_USER_ROLE(
ID VARCHAR(32)                   COMMENT '主键',  	
USER_ID VARCHAR(32)              COMMENT '用户ID',  
ROLE_ID VARCHAR(32)              COMMENT '角色ID',  

CREATE_TIME DATETIME             COMMENT '创建时间',
UPDATE_TIME DATETIME         	 COMMENT '更新时间',

PRIMARY KEY (ID)
) ENGINE=INNODB CHARSET=UTF8 COMMENT='用户角色表';



CREATE TABLE SYS_ROLE(
ID VARCHAR(32)               COMMENT '主键',  								
ROLE_NAME VARCHAR(20)        COMMENT '角色:普通用户/专家用户/管理员/黑名单',
							 
IS_STOP INT(1)               COMMENT '是否停用 0:否/1:是',   				
CREATE_TIME DATETIME         COMMENT '创建时间',  							
UPDATE_TIME DATETIME     	 COMMENT '更新时间',  							
PRIMARY KEY (ID)
)CHARSET=UTF8 COMMENT='角色表';



CREATE TABLE SYS_ROLE_MENU(
ID VARCHAR(32)											COMMENT '主键',  	
ROLE_ID VARCHAR(32)         							COMMENT '角色ID',  
MENU_ID VARCHAR(32)         							COMMENT '菜单ID',  

CREATE_TIME DATETIME        							COMMENT '创建时间',
UPDATE_TIME DATETIME    								COMMENT '更新时间',
PRIMARY KEY (ID)
)CHARSET=UTF8 COMMENT='角色菜单表';



CREATE TABLE SYS_MENU(	
ID VARCHAR(32)                        COMMENT '主键',
MENU_NAME VARCHAR(50)                 COMMENT '菜单名称',  							
MENU_URL VARCHAR(32)                  COMMENT '菜单URL地址',
						
PID VARCHAR(32)                       COMMENT '父菜单ID', 							
MENU_LEVEL INT(1)                     COMMENT '菜单层级 0:根菜单/1:父菜单/2:子菜单',	
INDEX_ORDER INT(2)                    COMMENT '菜单序号,默认未排序0',
 				
CREATE_TIME DATETIME                  COMMENT '创建时间', 							
UPDATE_TIME DATETIME                  COMMENT '更新时间', 							
PRIMARY KEY (ID)
)CHARSET=UTF8 COMMENT='菜单表';



CREATE TABLE SYS_SUBJECT(
ID VARCHAR(32)                                             COMMENT '主键', 				
SUBJECT_NAME VARCHAR(50)                                   COMMENT '学科名称',  			
PID VARCHAR(32)                                            COMMENT '父学科ID',
 			                                               
SUBJECT_LEVEL INT(1)                                       COMMENT '学科层级', 			
INDEX_ORDER INT(1)                                         COMMENT '学科序号,默认未排序0',
CREATE_TIME DATETIME                                       COMMENT '创建时间',
		
UPDATE_TIME DATETIME                                       COMMENT '更新时间', 			
PRIMARY KEY (ID)
)CHARSET=UTF8 COMMENT='学科表';



CREATE TABLE SYS_QUESTION(
ID VARCHAR(32)                                          COMMENT '主键', 				
QUESTION VARCHAR(1000)                                  COMMENT '问题文字描述', 		
QUESTION_PIC VARCHAR(50)                                COMMENT '问题辅助描述图片路径',

USER_ID VARCHAR(32)                                     COMMENT '提问者', 				
CREATE_TIME DATETIME                                    COMMENT '创建时间', 			
UPDATE_TIME DATETIME                                	COMMENT '更新时间', 			
PRIMARY KEY (ID)
)CHARSET=UTF8 COMMENT='问题表';



CREATE TABLE SYS_ANSWER(
ID VARCHAR(32)                			COMMENT '主键', 												
QUESTION_ID VARCHAR(32)       			COMMENT '回答对应问题的主键', 									
ANSWER VARCHAR(1000)          			COMMENT '回答文字描述', 										
						 
ANSWER_PIC VARCHAR(50)        			COMMENT '回答辅助图片路径', 									
STARS INT(1)                  			COMMENT '评价星级:1-5星级',									
COINS INT(11)                     		COMMENT '获得金币数10*K',  									
									
K DOUBLE(2,1)	                  		COMMENT '评价系数常量K:1星:0.1/2星:0.3/3星:0.5/4星:0.8/5星:1', 
CREATE_TIME DATETIME          			COMMENT '创建时间', 											
UPDATE_TIME DATETIME          			COMMENT '更新时间', 											
PRIMARY KEY (ID)
)CHARSET=UTF8 COMMENT='回答表';



CREATE TABLE SYS_ACCOUNT(
ID VARCHAR(32)                                 COMMENT '主键', 	
USER_ID  VARCHAR(32)                           COMMENT '用户主键',
TOTAL_COIN INT(5)                              COMMENT '金币总数',

MONEY INT(3)                                   COMMENT '账户余额',
CREATE_TIME DATETIME                           COMMENT '创建时间',
UPDATE_TIME DATETIME                           COMMENT '更新时间',
PRIMARY KEY (ID)
)CHARSET=UTF8 COMMENT='账户表';



CREATE TABLE SYS_LOGS(
ID VARCHAR(32)                                          COMMENT '主键', 	
USER_ID VARCHAR(32)                                     COMMENT '操作用户', 
IP_ADDRESS VARCHAR(25)                                  COMMENT '用户IP地址',

OP_MODULE VARCHAR(50)                                   COMMENT '操作模块', 
INTRODUCE VARCHAR(32)                                   COMMENT '操作描述', 
CREATE_TIME DATETIME                                    COMMENT '操作时间', 
PRIMARY KEY (ID)
)CHARSET=UTF8 COMMENT='日志表';
	
	

小程序:
    一.成本预算:
        1.前后台开发费用(90%,具体按功能多少和复杂程度而定);
        2.服务器费用(运行服务器和数据库服务器:存储空间大小,访问量大小);
        3.维护费(维护起止时间);
        4.域名费用;
        5.微信主体认证费用;


    二.利润评估:
        1.年运营费
        单用户单请求10金币,兑换为10/100=0.1元,则


    三.风险评估:


    四.项目策划:
        ***主要功能: 分类发送/返回信息, 消息推送, 微信登录, 扫码分享, 支付(充值兑换提现)
		发问者如果属于问题对应类,则加分正常, 否则减半.
		
		
        0.数据库设计(选用MySQL)
		
        1.架构设计,注意性能扩展,容量扩展和功能扩展.
            	性能扩展: 在用户量达到一定量级后,单独部署反向代理服务器,同时增加应用服务器,采用负载均衡分担访问压力;
            	容量扩展: 在存储图片服务器达到一定量级后,采用横向扩容机制扩容;
            	功能扩展: 在功能需要增加时,可随时增加,采用分布式;
            	可靠性: 部署集群;

        2.小程序开发设计需求文档
        3.产品原型设计
        4.UI设计
        5.前台开发
        6.后台开发
        7.服务器部署(小程序应用直接发布到微信服务器端, 前期使用两台云服务器:一台用于部署小程序服务, 一台用于部署数据库)
        8.产品测试
        9.产品修改
        10.正式上线	
