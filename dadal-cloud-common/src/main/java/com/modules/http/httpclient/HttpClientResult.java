/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.http.httpclient   
 * @author: Frankjiu
 * @date: 2020年8月24日
 * @version: V1.0
 */

package com.modules.http.httpclient;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 封装httpClient响应结果
 * @author: Frankjiu
 * @date: 2020年8月24日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpClientResult implements Serializable {

    private static final long serialVersionUID = 1L;

    public HttpClientResult(int code) {
        super();
        this.code = code;
    }

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

}
