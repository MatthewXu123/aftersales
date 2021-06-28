
package com.carel.controller.datamanage;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carel.controller.BaseController;
import com.carel.persistence.entity.pk.SparePartPK;
import com.carel.persistence.entity.product.SparePart;
import com.carel.util.JsonUtil;

/**
 * Description:
 * 
 * @author Matthew Xu
 * @date Nov 4, 2020
 */
@Controller
@RequestMapping("/sparepart")
public class SparePartDMController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(SparePartDMController.class);

	@GetMapping("/manage")
	public String getProduct() {
		try {
			return "/back/spare_part";
		} catch (Exception e) {
			logger.error("", e);
			return "/back/error";
		}
	}

	@GetMapping("/list")
	@ResponseBody
	public JSONArray getSparePartList() {
		try {
			return JsonUtil.listToJsonArray(sparePartService.getAll());
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
	
	@PostMapping("/update")
	@ResponseBody
	public JSONObject update(@RequestBody SparePart sparePart){
		try {
			sparePartService.saveOne(sparePart);
		} catch (Exception e) {
			logger.error("", e);
			return resultFactory.getFailResultJSON();
		}
		return resultFactory.getSuccessResultJSON();
	}
	
	@PostMapping("/save")
	@ResponseBody
	public JSONObject save(@RequestBody Map<String, String> _sparePart){
		try {
			SparePart sparePart = new SparePart();
			SparePartPK pk = new SparePartPK();
			pk.setProductCode(_sparePart.get("productCode"));
			pk.setPartCode(_sparePart.get("partCode"));
			sparePart.setPk(pk);
			sparePart.setDescription(_sparePart.get("description"));
			sparePart.setRequiredNum(_sparePart.get("requiredNum"));
			sparePartService.saveOne(sparePart);
		} catch (Exception e) {
			logger.error("", e);
			return resultFactory.getFailResultJSON();
		}
		return resultFactory.getSuccessResultJSON();
	}
}
