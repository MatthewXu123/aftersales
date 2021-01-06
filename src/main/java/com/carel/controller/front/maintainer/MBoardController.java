
package com.carel.controller.front.maintainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carel.controller.BaseController;
import com.carel.persistence.entity.community.Customer;
import com.carel.persistence.entity.product.Product;
import com.carel.util.JsonUtil;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 24, 2020
 */
@Controller
@RequestMapping("/mboard")
public class MBoardController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(MBoardController.class);
	
	@GetMapping
	public String getMboard(Model model){
		try {
			String loginCode = getLoginCode();
			Customer customer = customerService.getOneByLoginCode(loginCode);
			Integer pid = getPid();
			if(pid != null){
				Product product = productService.getOneById(pid);
				model.addAttribute("product", product);
				model.addAttribute("pid", pid);
			}
			model.addAttribute("isShownPolicy", customer == null ? false : customer.getIsShownPolicy());
			return "/front/mboard";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
		
	}
}
