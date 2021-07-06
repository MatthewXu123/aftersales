
package com.carel.controller.front.enduser;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.carel.controller.BaseController;
import com.carel.persistence.constant.EvaluationLevel;
import com.carel.persistence.constant.ProcessStatus;
import com.carel.persistence.entity.main.InstallationInfo;
import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.main.MaintenanceRecord;
import com.carel.persistence.entity.product.Product;
import com.carel.util.JsonUtil;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 24, 2020
 */
@Controller
@RequestMapping("/ueva")
public class UEvalutaionController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(UEvalutaionController.class);
	
	@PostMapping("/{issueId}")
	@ResponseBody
	public JSONObject updateIssueEvaluation(@PathVariable int issueId, @RequestParam EvaluationLevel evaluationLevel, @RequestParam String comment ){
		try {
			Issue issue = issueService.getOneById(issueId);
			issue.setProcessStatus(ProcessStatus.EVALUATED);
			issue.setEvaluationComment(comment);
			issue.setEvaluationLevel(evaluationLevel);
			if(evaluationLevel.equals(EvaluationLevel.UNSATISFIED)){
				sendBadEvaMail(issue);
			}
			issueService.saveIssue(issue);
			return JsonUtil.objectToJson(resultFactory.getSuccessResult());
		} catch (Exception e) {
			logger.error("",e);
			return JsonUtil.objectToJson(resultFactory.getFailResult());
		}
	}
	
	private void sendBadEvaMail(Issue issue){
		Product product = issue.getProduct();
		InstallationInfo installationInfo = product.getInstallationInfo();
		MaintenanceRecord mrecord = issue.getMaintenanceRecord();
		String mailTitle = mailMsgProperty.getBadEvaTitle();
		String mailContent = MessageFormat.format(mailMsgProperty.getBadEvaContent()
				, new Object[]{
						getRightContent(issue.getEvaluationComment()),
						getRightContent(installationInfo.getInstallerName()),
						getRightContent(installationInfo.getInstallerPhone()),
						getRightContent(installationInfo.getDetailedAddress()),
						getRightContent(installationInfo.getComment()),
						getRightContent(issue.getCode()),
						getRightContent(product.getSerialNumber()),
						getRightContent(product.getProductCode()),
						getRightContent(issue.getUsername()),
						getRightContent(issue.getUserPhone()),
						getRightContent(issue.gethAlarm().getDescription()),
						getRightContent(issue.getComment()),
						getRightContent(mrecord.getMaintainerName()),
						getRightContent(mrecord.getMaintainerPhone()),
						getRightContent(mrecord.gethAlarm().getDescription()),
						getRightContent(mrecord.getComment())
						
				});
		mailService.sendSimpleMail(null, null, mailTitle, mailContent);
	}
	
	private String getRightContent(String content){
		return StringUtils.isBlank(content) ? "Not Filled" : content;
	}
}
