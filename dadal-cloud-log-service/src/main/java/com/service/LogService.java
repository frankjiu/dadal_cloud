/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.service   
 * @author: Frankjiu
 * @date: 2020年6月1日
 * @version: V1.0
 */

package com.service;

import java.util.List;

import com.entity.AppLog;

/**
 * @Description: 日志接口
 * @author: Frankjiu
 * @date: 2020年6月1日
 */
public interface LogService {

	public void delete();

	public int insert(AppLog appLog);

	public int insertBatch(List<AppLog> appLog);

	public int getTotal(AppLog appLog);

	public List<AppLog> getList(int startIndex, int pageSize, AppLog appLog);

}
