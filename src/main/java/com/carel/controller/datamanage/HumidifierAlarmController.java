
package com.carel.controller.datamanage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 * @author Matthew Xu
 * @date Nov 3, 2020
 */
@Controller
@RequestMapping("/halarms")
public class HumidifierAlarmController {

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
	
	@GetMapping("/hut")
	public String getHUT(){
		try {
			return "/back/halarms_hut";
		} catch (Exception e) {
			logger.error("",e);
			return "/back/error";
		}
	}
}
