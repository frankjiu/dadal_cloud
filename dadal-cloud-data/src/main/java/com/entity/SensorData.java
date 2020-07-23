/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.entity   
 * @author: Frankjiu
 * @date: 2020年7月23日
 * @version: V1.0
 */

package com.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @Description: 传感数据实体类
 * @author: Frankjiu
 * @date: 2020年7月23日
 */
@Data
@Builder
public class SensorData {

	private String data_no;
	private Double temperature;
	private Double humidity;

}
