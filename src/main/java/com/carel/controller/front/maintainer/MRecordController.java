
package com.carel.controller.front.maintainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.carel.controller.BaseController;
import com.carel.persistence.constant.ProcessStatus;
import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.main.MaintenanceRecord;
import com.carel.persistence.entity.product.Product;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 24, 2020
 */
@Controller
@RequestMapping("/mrecord")
public class MRecordController extends BaseController{

	private final Logger logger = LoggerFactory.getLogger(MRecordController.class);
	
	@GetMapping
	public String getSpecifiedRecord(@RequestParam(required = false) String issueCode, Model model){
		try {
			Issue issue = issueService.getOneByCode(issueCode);
			model.addAttribute("issueCode", issueCode);
			
			model.addAttribute("issue", issue);
			MaintenanceRecord maintenanceRecord = maintenanceRecordService.getOneByIssueCode(issueCode);
			if(maintenanceRecord != null){
				model.addAttribute("maintenanceRecord", maintenanceRecord);
				model.addAttribute("mrecordId", maintenanceRecord.getId());
				model.addAttribute("mrecordAlarmId", maintenanceRecord.gethAlarm().getId());
				model.addAttribute("mrecordComment", maintenanceRecord.getComment());
			}
			return "/front/mrecord";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
		
	}
	
	@PostMapping
	public String updateUserIssue(@RequestParam String issueCode,
			MaintenanceRecord record,
			@RequestParam MultipartFile p1,
			@RequestParam MultipartFile p2,
			@RequestParam MultipartFile p3,
			@RequestParam(required = false) Integer alarmId,
			@RequestParam Integer photo1Val,
			@RequestParam Integer photo2Val,
			@RequestParam Integer photo3Val){
		try {
			Integer pid = getPid();
			Product product = productService.getOneById(pid);
			Issue issue = issueService.getOneByCode(issueCode);
			MaintenanceRecord oldRecord = maintenanceRecordService.getOneByIssueCode(issueCode);
			
			boolean isFirstSubmit = oldRecord == null;
			if(isFirstSubmit){
				record.setPhoto1(p1.getBytes());
				record.setPhoto2(p2.getBytes());
				record.setPhoto3(p3.getBytes());
				record.setProduct(product);
				record.setIssue(issue);
				if(alarmId != null)
					record.sethAlarm(humidifierAlarmService.getOneById(alarmId));
				if(record.getProcessStatus().equals(ProcessStatus.FINISHED))
					issue.setProcessStatus(ProcessStatus.FINISHED);
				maintenanceRecordService.saveOne(record);
			}else{
				//If we need delete the original photo or update the new photo...
				if(photo1Val == 1 || (photo1Val == 0 && p1.getBytes().length != 0)){
					record.setPhoto1(p1.getBytes());
				}else{
					record.setPhoto1(oldRecord.getPhoto1());
				}
				if(photo2Val == 1 || (photo2Val == 0 && p2.getBytes().length != 0)){
					record.setPhoto2(p2.getBytes());
				}else{
					record.setPhoto2(oldRecord.getPhoto2());
				}
				if(photo3Val == 1 || (photo3Val == 0 && p3.getBytes().length != 0)){
					record.setPhoto3(p3.getBytes());
				}else{
					record.setPhoto3(oldRecord.getPhoto3());
				}
				record.setId(oldRecord.getId());
				record.setIssue(issue);
				record.setProduct(product);
				if(alarmId != null)
					record.sethAlarm(humidifierAlarmService.getOneById(alarmId));
				if(record.getProcessStatus().equals(ProcessStatus.FINISHED))
					issue.setProcessStatus(ProcessStatus.FINISHED);
				else
					record.setProcessStatus(oldRecord.getProcessStatus());
				maintenanceRecordService.saveOne(record);
			}
			return "redirect:/missue";
		} catch (Exception e) {
			logger.error("",e);
			return "/front/error";
		}
	}
}
