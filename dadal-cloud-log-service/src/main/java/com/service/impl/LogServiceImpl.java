/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.service.impl   
 * @author: Frankjiu
 * @date: 2020年6月1日
 * @version: V1.0
 */

package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.AppLog;
import com.mapper.LogMapper;
import com.service.LogService;

/**
 * @Description: 日志接口实现
 * @author: Frankjiu
 * @date: 2020年6月1日
 */
@Service
public class LogServiceImpl implements LogService {

	@Autowired
	LogMapper logMapper;

	/**
	 * @return 日志记录
	 */
	@Override
	@Transactional
	public void delete() {
		logMapper.delete();
	}

	/**
	 * @return 日志记录
	 */
	@Override
	@Transactional
	public int insert(AppLog appLog) {
		return logMapper.insert(appLog);
	}

	/**
	 * @return 日志批量导入
	 */
	@Override
	@Transactional
	public int insertBatch(List<AppLog> list) {
		return logMapper.insertBatch(list);
	}

	/**
	 * 日志总量统计
	 */
	@Override
	public int getTotal(AppLog appLog) {
		return logMapper.getTotal(appLog);
	}

	/**
	 * 日志分页查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<AppLog> getList(int startIndex, int pageSize, AppLog appLog) {
		return logMapper.getList(startIndex, pageSize, appLog);
	}

}
