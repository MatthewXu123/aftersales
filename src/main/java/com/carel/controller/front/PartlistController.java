
package com.carel.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 * @author Matthew Xu
 * @date Nov 4, 2020
 */
@Controller
@RequestMapping("/partlist")
public class PartlistController {

	@GetMapping
	public String getVideo(){
		return "/front/partlist";
	}
}