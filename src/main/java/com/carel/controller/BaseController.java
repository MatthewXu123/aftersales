
package com.carel.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carel.component.ResultFactory;
import com.carel.property.wxcpmsg.WxCpMsgProperty;
import com.carel.service.CustomerService;
import com.carel.service.HumidifierAlarmService;
import com.carel.service.InstallationInfoService;
import com.carel.service.IssueService;
import com.carel.service.MaintenanceRecordService;
import com.carel.service.PhotoService;
import com.carel.service.ProductInfoService;
import com.carel.service.ProductService;
import com.carel.service.SalesService;
import com.carel.wxcp.service.WxCpMsgService;

import me.chanjar.weixin.cp.api.WxCpDepartmentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;

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
	protected ServletContext servletContext;
	
	@Autowired
	protected HttpSession httpSession;
	
	@Autowired
	protected ProductService productService;
	
	@Autowired
	protected IssueService issueService;
	
	@Autowired
	protected PhotoService photoService;
	
	@Autowired
	protected CustomerService customerService;
	
	@Autowired
	protected SalesService salesService;
	
	@Autowired
	protected InstallationInfoService installationInfoService;
	
	@Autowired
	protected MaintenanceRecordService maintenanceRecordService;
	
	@Autowired
	protected HumidifierAlarmService humidifierAlarmService;
	
	@Autowired
	protected ProductInfoService productInfoService;
	
	@Autowired 
	protected WxCpConfigStorage wxCpConfigStorage;
	
	@Autowired
	protected WxCpService wxCpService;
	
	@Autowired
	protected WxCpMsgService wxCpMsgService;
	
	@Autowired
	protected WxCpDepartmentService wxCpDepartmentService;
	
	@Autowired
	protected WxCpMsgProperty wxCpMsgProperty;
	
	protected Integer getPid(){
		return (Integer) httpSession.getAttribute("pid");
	}
	
	protected String getLoginCode(){
		return (String) httpSession.getAttribute("loginCode");
	}
	
	
}
