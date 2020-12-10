
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
@RequestMapping("/productInfo")
public class ProductInfoController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(ProductInfoController.class);
	
	@GetMapping("/manage")
	public String getProduct(){
		try {
			return "/back/productInfo";
		} catch (Exception e) {
			logger.error("",e);
			return "/back/error";
		}
	}
	
	@GetMapping("/list")
	@ResponseBody
	public JSONArray getList(){
		try {
			JSONArray listToJsonArray = JsonUtil.listToJsonArray(productInfoService.getAll());
			return listToJsonArray;
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
		
	}
}
