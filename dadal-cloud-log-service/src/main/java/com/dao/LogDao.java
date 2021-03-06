/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.dao   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月3日上午12:02:36
 * @version V1.0
 */

package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.entity.AppLog;

/**
 * @author: Frankjiu
 * @date: 2020年6月1日
 */
@Mapper
public interface LogDao {

	int delete();

	int insert(AppLog appLog);

	int insertBatch(List<AppLog> list);

	int getTotal(@Param("appLog") AppLog appLog);

	List<AppLog> getList(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize, @Param("appLog") AppLog appLog);

}
