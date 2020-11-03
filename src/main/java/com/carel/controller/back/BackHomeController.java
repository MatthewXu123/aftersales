
package com.carel.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 28, 2020
 */
@Controller
public class BackHomeController {

	@GetMapping("/backhome")
	public String getHome(){
		return "/back/home";
	}
}
