
package com.carel.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 14, 2020
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String getLogin(){
		return "/back/login";
	}
	
	@PostMapping
	public String getLoginVerified(@RequestParam String username, @RequestParam String password){
		if(username.equals("admin") && password.equals("123456ass"))
			return "/back/index";
		else
			return "/back/login";
	}
	
}
