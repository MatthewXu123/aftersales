
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
 * @date Oct 15, 2020
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@GetMapping("/manage")
	public String getCustomer(){
		try {
			return "/back/customer";
		} catch (Exception e) {
			logger.error("",e);
			return "/back/error";
		}
	}
	
	@GetMapping("/list")
	@ResponseBody
	public JSONArray getCustomerList(){
		try {
			JSONArray listToJsonArray = JsonUtil.listToJsonArray(customerService.getAll());
			return listToJsonArray;
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
		
	}
}
