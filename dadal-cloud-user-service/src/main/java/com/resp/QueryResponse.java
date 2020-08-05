/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.resp   
 * @author: Frankjiu
 * @date: 2020年7月21日
 * @version: V1.0
 */

package com.resp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryResponse {

	@GetMapping("/getUser/{id}")
	public ResultOld getUser(@PathVariable("id") Integer id) {

		try {
			List<String> list = new ArrayList<>();
			list.add("a1Task");
			list.add("b2Task");
			list.add("d3Task");
			return ResultOld.suc("").data("data", list);
		} catch (Exception e) {
			return ResultOld.err("");
		}
	}

}
