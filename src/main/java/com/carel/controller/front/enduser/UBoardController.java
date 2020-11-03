
package com.carel.controller.front.enduser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carel.controller.BaseController;
import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.product.Product;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 24, 2020
 */
@Controller
@RequestMapping("/uboard")
public class UBoardController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(UBoardController.class);
	
	@GetMapping
	public String getUboard(Model model){
		try {
			Integer pid = getPid();
			if(pid != null){
				Product product = productService.getOneById(pid);
				Issue issue = issueService.getOneByPid(pid);
				model.addAttribute("issue", issue);
				model.addAttribute("product", product);
			}
			return "/front/uboard";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
		
	}
}
