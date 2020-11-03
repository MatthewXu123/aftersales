
package com.carel.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 16, 2020
 */
@Controller
@RequestMapping("/manual")
public class ManualController {

	@GetMapping
	public String getManual(){
		return "/front/manual";
	}
}
