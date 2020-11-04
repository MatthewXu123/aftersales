
package com.carel.controller.datamanage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 * @author Matthew Xu
 * @date Nov 4, 2020
 */
@Controller
@RequestMapping("/productinfo")
public class ProductInfoController {

	private final Logger logger = LoggerFactory.getLogger(ProductInfoController.class);
	
	@GetMapping("/manage")
	public String getProduct(){
		try {
			return "/back/productinfo";
		} catch (Exception e) {
			logger.error("",e);
			return "/back/error";
		}
	}
}
