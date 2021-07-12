
package com.carel.scheduledjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.carel.service.MailService;
import com.carel.service.ReportService;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 12, 2021
 */
@Component
public class MailScheduledJob {

	@Autowired
	MailService mailService;
	
	@Autowired
	ReportService reportService;
	
	@Scheduled(cron = "0 0 14 ? * 6 ")
	public void sendWeeklyReport(){
		reportService.sendWeeklyReport();
	}
}
