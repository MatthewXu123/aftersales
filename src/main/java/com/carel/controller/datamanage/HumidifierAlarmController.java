
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
 * @date Nov 3, 2020
 */
@Controller
@RequestMapping("/halarms")
public class HumidifierAlarmController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(HumidifierAlarmController.class);
	
	@GetMapping("/huh")
	public String getHUH(){
		try {
			return "/back/halarms_huh";
		} catch (Exception e) {
			logger.error("",e);
			return "/back/error";
		}
	}
	
	@GetMapping("/huh/list")
	@ResponseBody
	public JSONArray getHuhList(){
		JSONArray listToJsonArray = new JSONArray();
		try {
			listToJsonArray = JsonUtil.listToJsonArray(humidifierAlarmService.getAllByType("huh"));
		} catch (Exception e) {
			logger.error("",e);
		}
		return listToJsonArray;
	}
	
	
	@GetMapping("/hut")
	public String getHUT(){
		try {
			return "/back/halarms_hut";
		} catch (Exception e) {
			logger.error("",e);
			return "/back/error";
		}
	}
	
	@GetMapping("/hut/list")
	@ResponseBody
	public JSONArray getHutList(){
		JSONArray listToJsonArray = new JSONArray();
		try {
			listToJsonArray = JsonUtil.listToJsonArray(humidifierAlarmService.getAllByType("hut"));
		} catch (Exception e) {
			logger.error("",e);
		}
		return listToJsonArray;
	}
}
