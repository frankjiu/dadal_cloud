/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @author: Frankjiu
 * @date: 2020年8月26日
 * @version: V1.0
 */

package com.modules.sys.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: xxx实体类
 * @author: Frankjiu
 * @date: 2020年8月26日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Demo {

    private String id;
    private String cardName;
    private String cardNumber;
    private LocalDateTime createTime;

}
