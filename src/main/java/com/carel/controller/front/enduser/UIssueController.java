package com.carel.controller.front.enduser;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.carel.controller.BaseController;
import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.product.Product;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 24, 2020
 */
@Controller
@RequestMapping("/uissue")
public class UIssueController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(UIssueController.class);
	
	@GetMapping(value = {"/{issueCode}", "/"})
	public String getUIssue(@PathVariable(required = false) String issueCode, Model model){
		try {
			Integer pid = getPid();
			if(pid != null){
				Product product = productService.getOneById(pid);
				model.addAttribute("product", product);
				Issue issue = issueCode == null ? null : issueService.getOneByCode(issueCode);
				if(issue != null ){
					model.addAttribute("issue", issue);
					model.addAttribute("issueId", issue.getId());
					model.addAttribute("issueCode", issue.getCode());
					model.addAttribute("issueStatus", issue.getProcessStatus());
					if(issue.gethAlarm() != null)
						model.addAttribute("issueHalarmId", issue.gethAlarm().getId());
				}
				
			}
			
			return "/front/uissue";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
		
	}
	
	@PostMapping(value = {"/{issueCode}", "/"})
	public String updateUserIssue(
			@PathVariable(required = false) String issueCode,
			@Valid Issue userIssue,
			@RequestParam(required = false) Integer alarmId,
			@RequestParam MultipartFile p1,
			@RequestParam MultipartFile p2,
			@RequestParam MultipartFile p3,
			@RequestParam Integer photo1Val,
			@RequestParam Integer photo2Val,
			@RequestParam Integer photo3Val,
			BindingResult result,
			HttpServletRequest request){
		try {
			// Error handle
			if(result.hasErrors())
				return "/front/uissue";
			
			Integer pid = getPid();
			Product product = productService.getOneById(pid);
			boolean isFirstSubmit = issueCode == null || issueService.getOneByCode(issueCode) == null;
			if(isFirstSubmit){
				userIssue.setPhoto1(p1.getBytes());
				userIssue.setPhoto2(p2.getBytes());
				userIssue.setPhoto3(p3.getBytes());
				userIssue.setProduct(product);
				userIssue.setCode(userIssue.getCode());
				if(alarmId != null)
					userIssue.sethAlarm(humidifierAlarmService.getOneById(alarmId));
				issueCode = issueService.saveNewPendingIssue(userIssue).getCode();
				
				//When the new issue is submitted, we send the message to the related customer.
				wxCpMsgService.sendNewIssueMsg(wxCpMsgProperty.getNewIssue() + getDistributionURL(request, pid, issueCode), 
						product.getOwnerCustomer().getWxcpDeptId(), 
						userIssue, 
						product);
			}else{
				Issue oldIssue = issueService.getOneByCode(issueCode);
				//If we need delete the original photo or update the new photo...
				if(photo1Val == 1 || (photo1Val == 0 && p1.getBytes().length != 0)){
					userIssue.setPhoto1(p1.getBytes());
				}else{
					userIssue.setPhoto1(oldIssue.getPhoto1());
				}
				if(photo2Val == 1 || (photo2Val == 0 && p2.getBytes().length != 0)){
					userIssue.setPhoto2(p2.getBytes());
				}else{
					userIssue.setPhoto2(oldIssue.getPhoto2());
				}
				if(photo3Val == 1 || (photo3Val == 0 && p3.getBytes().length != 0)){
					userIssue.setPhoto3(p3.getBytes());
				}else{
					userIssue.setPhoto3(oldIssue.getPhoto3());
				}
				userIssue.setProduct(product);
				userIssue.setCode(oldIssue.getCode());
				userIssue.setProcessStatus(oldIssue.getProcessStatus());
				if(alarmId != null)
					userIssue.sethAlarm(humidifierAlarmService.getOneById(alarmId));
				issueCode = issueService.saveIssue(userIssue).getCode();
			}
			return "redirect:/uissue/" + issueCode;
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
	}
	
	
	private String getDistributionURL(HttpServletRequest request, Integer pid, String issueCode){
		StringBuffer requestURL = request.getRequestURL();
		String url = requestURL.substring(0, requestURL.indexOf("uissue"));
		url += "missue/" + issueCode + "/distribution?isWxcp=true&&pid=" + pid;
		return "\n<a href=\"" + url + "\">点击进行分配</a>";
	}
	
}
