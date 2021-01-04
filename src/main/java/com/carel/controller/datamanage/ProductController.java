
package com.carel.controller.datamanage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.carel.controller.BaseController;
import com.carel.util.JsonUtil;

/**
 * Description:
 * @author Matthew Xu
 * @date Nov 4, 2020
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping("/manage")
	public String getProduct(){
		try {
			return "/back/product";
		} catch (Exception e) {
			logger.error("",e);
			return "/back/error";
		}
	}
	
	@GetMapping("/list")
	@ResponseBody
	public JSONArray getList(){
		JSONArray listToJsonArray = new JSONArray();
		try {
			listToJsonArray = JsonUtil.listToJsonArray(productService.getAll());
		} catch (Exception e) {
			logger.error("",e);
		}
		return listToJsonArray;
	}
}
