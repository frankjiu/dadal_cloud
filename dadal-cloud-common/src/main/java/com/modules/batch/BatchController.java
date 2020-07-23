/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.batch   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月13日上午1:17:52
 * @version V1.0
 */

package com.modules.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * start batch
 * 
 * @author: Frankjiu
 * @date: 2020年4月13日 上午1:17:52
 */
@RestController
public class BatchController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

	@GetMapping("/doBatch")
	public void doBatch() {
		try {
			JobParameters jobParameters = new JobParametersBuilder().addString("para1", "value1").toJobParameters();
			jobLauncher.run(job, jobParameters);
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>batch execute completed: data inited successed.");
		} catch (Exception e) {
			logger.info("===>>> Exception occured while doBatch:{}", e.getMessage(), e);
		}
	}

}
