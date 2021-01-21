/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @author: Frankjiu
 * @date: 2020年8月26日
 * @version: V1.0
 */

package com.modules.export2;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    private Integer id;
    private String cardName;
    private Long cardNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String createTime;

}
