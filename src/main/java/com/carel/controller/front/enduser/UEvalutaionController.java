
package com.carel.controller.front.enduser;

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
import com.carel.persistence.entity.main.Issue;
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
			issueService.saveIssue(issue);
			return JsonUtil.objectToJson(resultFactory.getSuccessResult());
		} catch (Exception e) {
			logger.error("",e);
			return JsonUtil.objectToJson(resultFactory.getFailResult());
		}
	}
}
