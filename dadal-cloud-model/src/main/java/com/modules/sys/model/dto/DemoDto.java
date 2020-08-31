/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @author: Frankjiu
 * @date: 2020年8月26日
 * @version: V1.0
 */

package com.modules.sys.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @Description: xxx参数
 * @author: Frankjiu
 * @date: 2020年8月26日
 */
@Data
public class DemoDto {

    private String id;
    private String cardName;
    private String cardNumber;
    private LocalDateTime createTime;

}
