/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年6月1日
 * @version: V1.0
 */

package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: Index Controller
 * @author: Frankjiu
 * @date: 2020年6月1日
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@GetMapping("/toIndex")
	public ModelAndView toIndex(@RequestParam(value = "name", required = false, defaultValue = "guest") String name) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", "guests");
		mv.setViewName("index");
		return mv;
	}

}
