
package com.carel.controller.front.maintainer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carel.controller.BaseController;
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
				if(customerService.getOneByLoginCode(loginCode) != null){
					Issue issue = issueService.getOneByPid(pid);
					if(issue != null)
						issueList.add(issue);
				}else if(customerService.getOneByLoginCode(loginCode) != null){
					issueList = issueService.getAllByPid(pid);
				}
				model.addAttribute("product", product);
			}
			model.addAttribute("issueList", issueList);
			return "/front/missue";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
		
	}
	
}
