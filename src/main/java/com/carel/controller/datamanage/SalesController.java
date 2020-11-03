
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
 * @date Oct 19, 2020
 */
@Controller
@RequestMapping("/sales")
public class SalesController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(SalesController.class);
	
	@GetMapping("/manage")
	public String getCustomer(){
		try {
			return "/back/sales";
		} catch (Exception e) {
			logger.error("",e);
			return "/back/error";
		}
	}
	
	
	@GetMapping("/list")
	@ResponseBody
	public JSONArray getSalesList(){
		try {
			return JsonUtil.listToJsonArray(salesService.getAll());
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
	}
	
	@GetMapping("/sales_customer")
	public String getCustomerSales(){
		try {
			return "/back/sales_customer";
		} catch (Exception e) {
			logger.error("",e);
			return "/back/error";
		}
	}
}
