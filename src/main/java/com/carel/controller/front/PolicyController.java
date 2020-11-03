
package com.carel.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 * @author Matthew Xu
 * @date Aug 28, 2020
 */
@Controller
@RequestMapping("/policy")
public class PolicyController {

	@GetMapping
	public String getPolicy(){
		return "/front/policy";
	}
	
}
