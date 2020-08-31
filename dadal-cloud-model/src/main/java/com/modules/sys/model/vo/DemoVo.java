/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @author: Frankjiu
 * @date: 2020年8月26日
 * @version: V1.0
 */

package com.modules.sys.model.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @Description: xxx页面数据
 * @author: Frankjiu
 * @date: 2020年8月26日
 */
@Data
@Builder
public class DemoVo {

    private String id;
    private String cardName;
    private String cardNumber;

}
