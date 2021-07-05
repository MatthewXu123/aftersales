
package com.carel.controller.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.carel.controller.BaseController;
import com.carel.persistence.entity.product.Product;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 17, 2020
 */
@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@GetMapping
	public String getHome(@RequestParam String sn, @RequestParam String pc, Model model){
		try {
			Product product = productService.getOneBySNAndPCode(sn, pc);
			int pid = product.getId();
			httpSession.setAttribute("pid", pid);
			model.addAttribute("pid", pid);
			return "/front/role";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
	}
	
//	@GetMapping
//	public String getHome(){
//		try {
//			return "/front/home";
//		} catch (Exception e) {
//			logger.error("",e);
//			return "/front/error";
//		}
//	}
	
	@GetMapping("/role")
	public String getRole(){
		try {
			return getPid() != null ? "/front/role" : "/front/home";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
	}
	
	@PostMapping("/sn_verify")
	@ResponseBody
	public JSONObject getSNVerified(@RequestParam String sn){
		try {
			Product product = productService.getOneBySN(sn);
			if(product != null){
				httpSession.setAttribute("pid", product.getId());
				return resultFactory.getSuccessResultJSON();
			}
		} catch (Exception e) {
			logger.error("",e);
			return resultFactory.getFailResultJSON();
		}
		return resultFactory.getFailResultJSON();
	}
}
