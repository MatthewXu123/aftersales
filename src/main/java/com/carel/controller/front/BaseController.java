
package com.carel.controller.front;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carel.component.ResultFactory;
import com.carel.service.DistributorService;
import com.carel.service.InstallationInfoService;
import com.carel.service.IssueService;
import com.carel.service.MaintenanceRecordService;
import com.carel.service.PhotoService;
import com.carel.service.ProductService;
import com.carel.service.SubDistributorService;

/**
 * Description:
 * @author Matthew Xu
 * @date Aug 26, 2020
 */
@Component
public class BaseController {

	@Autowired
	protected ResultFactory resultFactory;
	
	@Autowired
	protected HttpSession httpSession;
	
	@Autowired
	protected ProductService productService;
	
	@Autowired
	protected IssueService issueService;
	
	@Autowired
	protected PhotoService photoService;
	
	@Autowired
	protected DistributorService distributorService;
	
	@Autowired
	protected SubDistributorService subDistributorService;
	
	@Autowired
	protected InstallationInfoService installationInfoService;
	
	@Autowired
	protected MaintenanceRecordService maintenanceRecordService;
	
	protected Integer getPid(){
		return (Integer) httpSession.getAttribute("pid");
	}
	
	protected String getLoginCode(){
		return (String) httpSession.getAttribute("loginCode");
	}
}
