/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @author: Frankjiu
 * @date: 2020年8月26日
 * @version: V1.0
 */

package com.modules.sys.service;

import java.util.List;

import com.modules.sys.model.dto.DemoDto;
import com.modules.sys.model.entity.Demo;

/**
 * @author: Frankjiu
 * @date: 2020年8月26日
 */
public interface DemoService {

    /** 获取所有demo表数据 */
    List<Demo> getDemoList(DemoDto demoDto) throws Exception;

}
