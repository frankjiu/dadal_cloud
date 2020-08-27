/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.sys.controller   
 * @author: Frankjiu
 * @date: 2020年8月27日
 * @version: V1.0
 */

package com.modules.sys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年8月27日
 */
@RestController
@RequestMapping("index")
public class IndexController {

    @GetMapping("toIndex")
    public String toIndex() {
        return "here is the index page";
    }

}
