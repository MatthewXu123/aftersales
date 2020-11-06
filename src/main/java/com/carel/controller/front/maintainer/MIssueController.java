
package com.carel.controller.front.maintainer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.carel.controller.BaseController;
import com.carel.persistence.constant.ProcessStatus;
import com.carel.persistence.entity.community.Customer;
import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.product.Product;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 24, 2020
 */
@Controller
@RequestMapping("/missue")
public class MIssueController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(MIssueController.class);
	
	@GetMapping
	public String getIssue(Model model){
		try {
			String loginCode = getLoginCode();
			List<Issue> issueList = new ArrayList<>();
			Integer pid = getPid();
			if(pid != null){
				Product product = productService.getOneById(pid);
				Customer customer = customerService.getOneByLoginCode(loginCode);
				Issue issue = issueService.getOneByPid(pid);
				Issue issue2 = issueService.getOneByCustomerId(customer.getId());
				if((issue != null && customer.getIsOwnerCustomer() && customer.getId() == product.getOwnerCustomer().getId()) || (issue != null && issue2 != null && issue.getCode().equals(issue2.getCode())))
					issueList.add(issue);
				/*if(customerService.getOneByLoginCode(loginCode) != null){
					Issue issue = issueService.getOneByPid(pid);
					if(issue != null)
						issueList.add(issue);
				}else if(customerService.getOneByLoginCode(loginCode) != null){
					issueList = issueService.getAllByPid(pid);
				}*/
				model.addAttribute("product", product);
			}
			model.addAttribute("issueList", issueList);
			return "/front/missue";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
		
	}
	
	@GetMapping(value = {"/{issueCode}/distribution"})
	public String getUIssueDistribution(@ModelAttribute @PathVariable(required = false) String issueCode, 
			@RequestParam(required = false) Integer pid, 
			@RequestParam(required = false) Boolean isWxcp,
			Model model){
		try {
			if(pid == null)
				pid = getPid();
			else
				httpSession.setAttribute("pid", pid);
			
			Issue issue = issueCode == null ? null : issueService.getOneByCode(issueCode);
			if(issue == null || issue.getCustomer() != null)
				return "/front/error";
			if(pid != null){
				Product product = productService.getOneById(pid);
				model.addAttribute("isWxcp", isWxcp);
				model.addAttribute("customerList", wxCpDepartmentService.list(Long.valueOf(product.getOwnerCustomer().getWxcpDeptId())));
			}
			return "/front/missuedis";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
		
	}
	
	@PostMapping(value = {"/{issueCode}/distribution"})
	@ResponseBody
	public JSONObject postUIssueDistribution(@ModelAttribute @PathVariable(required = false) String issueCode, 
			@RequestParam Integer repairCustomerId, 
			Model model){
		try {
			Integer pid = getPid();
			if(pid != null){
				Issue issue = issueCode == null ? null : issueService.getOneByCode(issueCode);
				Product product = productService.getOneById(pid);
				String ownerCustomerId = product.getOwnerCustomer().getWxcpDeptId();
				Customer repairCustomer = customerService.getOneByDeptId(String.valueOf(repairCustomerId));
				if(!String.valueOf(repairCustomerId).equals(ownerCustomerId)){
					wxCpMsgService.sendNewIssueMsg(wxCpMsgProperty.getNewIssue(),
							repairCustomer.getWxcpDeptId(), 
							issue, 
							product);
				}
				issue.setCustomer(repairCustomer);
				issue.setProcessStatus(ProcessStatus.IN_PROGRESS);
				issueService.saveIssue(issue);
			}
		} catch (Exception e) {
			logger.error("",e);
			return resultFactory.getFailResultJSON();
		}
		return resultFactory.getSuccessResultJSON();
		
	}
	
}
