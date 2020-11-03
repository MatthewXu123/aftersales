
package com.carel.controller.front.maintainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.carel.controller.BaseController;
import com.carel.persistence.entity.main.InstallationInfo;
import com.carel.persistence.entity.product.Product;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 24, 2020
 */
@Controller
@RequestMapping("/minstall")
public class MInstallController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(MInstallController.class);
	
	@GetMapping
	public String getInstallationInfo(Model model){
		try {
			Integer pid = getPid();
			if(pid != null){
				InstallationInfo installationInfo = installationInfoService.getOneByPid(pid);
				model.addAttribute("installationInfo", installationInfo);
			}
			return "/front/minstall";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
		
	}
	
	@PostMapping
	@ResponseBody
	public JSONObject updateInstallationInfo(@ModelAttribute InstallationInfo installationInfo){
		try {
			Integer pid = getPid();
			if(pid != null){
				Product product = productService.getOneById(pid);
				installationInfo.setProduct(product);
				installationInfo = installationInfoService.saveOne(installationInfo);
			}
			return resultFactory.getSuccessResultJSON();
		} catch (Exception e) {
			logger.error("",e);
			return resultFactory.getFailResultJSON();
		}
		
	}
}
