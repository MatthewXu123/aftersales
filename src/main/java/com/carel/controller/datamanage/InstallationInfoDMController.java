
package com.carel.controller.datamanage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carel.controller.BaseController;
import com.carel.persistence.entity.main.InstallationInfo;
import com.carel.util.JsonUtil;

/**
 * Description:
 * 
 * @author Matthew Xu
 * @date Jul 7, 2021
 */
@Controller
@RequestMapping("/dm_installationinfo")
public class InstallationInfoDMController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/manage")
	public String getCustomer(){
		try {
			return "/back/installation_info";
		} catch (Exception e) {
			logger.error("",e);
			return "/back/error";
		}
	}
	
	@GetMapping("/list")
	@ResponseBody
	public JSONArray getIssueList() {
		try {
			return JsonUtil.listToJsonArray(installationInfoService.getAll());
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
	
	@PostMapping("/save")
	@ResponseBody
	public JSONObject save(@RequestBody InstallationInfo installationInfo){
		try {
			installationInfoService.saveOne(installationInfo);
		} catch (Exception e) {
			logger.error("", e);
			return resultFactory.getFailResultJSON();
		}
		return resultFactory.getSuccessResultJSON();
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public JSONObject delete(@RequestParam String ids){
		try {
			for(String id : ids.split(","))
				installationInfoService.deleteInstallationInfoAndIssueAndMRecordById(Integer.valueOf(id));
		} catch (Exception e) {
			logger.error("", e);
			return resultFactory.getFailResultJSON();
		}
		return resultFactory.getSuccessResultJSON();
	}
	

}
