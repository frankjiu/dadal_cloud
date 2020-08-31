/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @author: Frankjiu
 * @date: 2020年8月26日
 * @version: V1.0
 */

package com.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.modules.sys.model.dto.DemoDto;
import com.modules.sys.model.entity.Demo;

/**
 * @author: Frankjiu
 * @date: 2020年8月26日
 */
@Mapper
public interface DemoDao {

    List<Demo> getDemoList(@Param("demoDto") DemoDto demoDto) throws Exception;

}
