/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.batch   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月13日上午12:26:45
 * @version V1.0
 */

package com.modules.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author: Frankjiu
 * @date: 2020年4月13日 上午12:26:45
 */
@Configuration
public class CsvBatchJobConfig {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Autowired
	DataSource dataSource;

	@Bean
	@StepScope
	FlatFileItemReader<User> itemReader() {
		FlatFileItemReader<User> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("data.csv"));
		DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer() {
			{
				setNames("id", "username", "address", "gender");
				setDelimiter("\t");
			}
		});
		lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {
			{
				setTargetType(User.class);
			}
		});
		reader.setLineMapper(lineMapper);
		return reader;
	}

	@Bean
	JdbcBatchItemWriter<User> jdbcBatchItemWriter() {
		JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<User>();
		writer.setDataSource(dataSource);
		writer.setSql("insert into batchuser(id ,username, address, gender) " + "values(:id, :username, :address, :gender)" + "");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		return writer;
	}

	@Bean
	Step csvStep() {
		return stepBuilderFactory.get("csvStep").<User, User> chunk(2).reader(itemReader()).writer(jdbcBatchItemWriter()).build();
	}

	@Bean
	Job csvJob() {
		return jobBuilderFactory.get("csvJob").start(csvStep()).build();
	}

}
