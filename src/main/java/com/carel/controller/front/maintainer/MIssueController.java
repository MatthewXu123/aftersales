
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

import com.carel.controller.BaseController;
import com.carel.persistence.entity.community.Customer;
import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.product.Product;

import me.chanjar.weixin.cp.bean.WxCpDepart;

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
				if(customer.getIsOwnerCustomer() || (issue2 != null && issue.getCode().equals(issue2.getCode())))
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
	public String getUIssueDistribution(@ModelAttribute @PathVariable(required = false) String issueCode, Model model){
		try {
			Integer pid = getPid();
			if(pid != null){
				Issue issue = issueCode == null ? null : issueService.getOneByCode(issueCode);
				Product product = productService.getOneById(pid);
				model.addAttribute("customerList", wxCpDepartmentService.list((long)product.getOwnerCustomer().getDeptId()));
			}
			return "/front/missuedis";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
		
	}
	
	@PostMapping(value = {"/{issueCode}/distribution"})
	public String postUIssueDistribution(@ModelAttribute @PathVariable(required = false) String issueCode, 
			@RequestParam Integer repairCustomerId, 
			Model model){
		try {
			Integer pid = getPid();
			if(pid != null){
/*				Issue issue = issueCode == null ? null : issueService.getOneByCode(issueCode);
				Product product = productService.getOneById(pid);
				if(!bindCustomerId.equals(disCustomerId)){
					Customer disCustomer = customerService.getOneById(disCustomerId);
					wxCpMsgService.sendMsgToParty(wxCpMsgProperty.getNewIssue(), 
							disCustomer.getDeptId(),
							new Object[]{
									issueCode,
									issue.getUsername(), 
									issue.getUserPhone(), 
									product.getInstallationAddress(),
									issue.getAlarmCode(),
									null,
									issue.getComment(),
									""
									});
				}
				model.addAttribute("deptList", wxCpDepartmentService.list(customerId));*/
			}
			return "/front/uissue";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
		
	}
	
}
