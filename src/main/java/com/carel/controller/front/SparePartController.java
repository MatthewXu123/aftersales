
package com.carel.controller.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carel.controller.BaseController;
import com.carel.persistence.entity.product.Product;

/**
 * Description:
 * @author Matthew Xu
 * @date Nov 4, 2020
 */
@Controller
@RequestMapping("/partlist")
public class SparePartController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(SparePartController.class);
	
	@GetMapping
	public String getPartList(Model model){
		try {
			Integer pid = getPid();
			if(pid != null){
				Product product = productService.getOneById(pid);
				model.addAttribute("spareparts", sparePartService.getAllByProductCode(product.getProductCode()));
			}
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
		return "/front/partlist";
	}
}