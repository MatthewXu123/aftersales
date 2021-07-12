
package com.carel.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.entity.main.InstallationInfo;
import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.main.MaintenanceRecord;
import com.carel.service.InstallationInfoService;
import com.carel.service.IssueService;
import com.carel.service.MailService;
import com.carel.service.MaintenanceRecordService;
import com.carel.service.ReportService;
import com.carel.util.DateUtil;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 12, 2021
 */
@Service
public class ReportServiceImpl implements ReportService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String SPLIT_LINE = "\n----------------------------------------------\n";
	
	private static final String SPLIT_LINE2 = "\n———————————————————————————————————————————————————————————————————————————————————————————————\n";
	
	@Autowired
	InstallationInfoService installationInfoService;
	
	@Autowired
	IssueService issueService;
	
	@Autowired
	MaintenanceRecordService maintenanceRecordService;
	
	@Autowired
	MailService mailService;
	
	@Override
	public void sendWeeklyReport() {
		try {
			Date end = new Date();
			Calendar instance = Calendar.getInstance();
			instance.setTime(end);
			instance.set(Calendar.DAY_OF_MONTH, instance.get(Calendar.DAY_OF_MONTH) + 1);
			instance.set(Calendar.HOUR_OF_DAY, 0);
			instance.set(Calendar.MINUTE, 0);
			instance.set(Calendar.SECOND, 0);
			end = instance.getTime();
			Date start = DateUtil.addDays(end, -7);
			
			String mailTitle = "After Sales System - Weekly Report[" 
					+ DateUtil.format(start, "dd/MM") + " - " + DateUtil.format(end, "dd/MM") + "]";
			String mailContent = "安装信息：";
			
			List<InstallationInfo> installationInfos = installationInfoService.getByCreateTimeBetween(start, end);
			for (InstallationInfo info : installationInfos) {
				mailContent += SPLIT_LINE + info.toString();
			}
			mailContent += installationInfos.size() == 0 ? "暂无" : "";
			mailContent += SPLIT_LINE2 + "报修：";
			
			List<Issue> issues = issueService.getIssueBetween(start, end);
			for (Issue issue : issues) {
				mailContent += SPLIT_LINE + issue.toString();
			}
			mailContent += issues.size() == 0 ? "暂无" : "";
			mailContent += SPLIT_LINE2 + "维修：";
			
			List<MaintenanceRecord> mrs = maintenanceRecordService.getByCreateTimeBetween(start, end);
			for (MaintenanceRecord mr : mrs) {
				mailContent += SPLIT_LINE + mr.toString();
			}
			mailContent += mrs.size() == 0 ? "暂无" : "";
			
			mailService.sendSimpleMail("916415285@qq.com", mailTitle, mailContent);
		} catch (Exception e) {
			logger.error("" ,e);
		}
	}

}
