
package com.carel.controller.front.maintainer;

import java.util.List;

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
import com.carel.persistence.entity.community.Customer;
import com.carel.persistence.entity.product.Product;

import me.chanjar.weixin.cp.bean.WxCpDepart;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 24, 2020
 */
@Controller
@RequestMapping("/mlogin")
public class MLoginController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(MLoginController.class);
	
	@GetMapping
	public String getMLogin(Model model){
		try {
			Integer pid = getPid();
			if(pid != null){
				Product product = productService.getOneById(pid);
				model.addAttribute("product", product);
			}
			return "/front/mlogin";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
		
	}
	
	@PostMapping
	@ResponseBody
	public JSONObject getVerified(@RequestParam String loginCode, Model model){
		try {
			Customer customer = customerService.getOneByLoginCode(loginCode);
			if(customer == null)
				return resultFactory.getFailResultJSON();
			else{
				// To bind the product with the owner customer.
				Integer pid = getPid();
				Product product = productService.getOneById(pid);
				if(product.getOwnerCustomer() == null){
					if(!customer.getIsOwnerCustomer()){
						long customerId = Long.valueOf(customer.getWxcpDeptId());
						List<WxCpDepart> list = wxCpDepartmentService.list(customerId);
						for (WxCpDepart wxCpDepart : list) {
							if(wxCpDepart.getId() == customerId){
								customer = customerService.getOneByDeptId(String.valueOf(wxCpDepart.getParentId()));
								break;
							}
						}
					}
					product.setOwnerCustomer(customer);
					productService.saveOne(product);
				}
					
				httpSession.setAttribute("loginCode", loginCode);
				return resultFactory.getSuccessResultJSON();
			}
		} catch (Exception e) {
			logger.error("",e);
			return resultFactory.getFailResultJSON();
		}
		
	}
}
