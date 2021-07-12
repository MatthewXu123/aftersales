
package com.carel.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.constant.ProcessStatus;
import com.carel.persistence.entity.main.Issue;
import com.carel.repository.IssueRepository;
import com.carel.repository.MaintenanceRecordRepository;
import com.carel.repository.ProductInfoRepository;
import com.carel.service.IssueService;
import com.carel.util.DateUtil;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 2, 2020
 */
@Service
public class IssueServiceImpl implements IssueService{

	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	ProductInfoRepository productInfoRepository;
	
	@Autowired
	MaintenanceRecordRepository maintenanceRecordRepository;

	@Override
	public Issue saveNewPendingIssue(Issue userIssue) {
		Issue existedIssue = issueRepository.findByProductIdAndProcessStatus(userIssue.getProduct().getId(), ProcessStatus.PENDING);
		if(existedIssue == null){
			userIssue.setProcessStatus(ProcessStatus.PENDING);
			return issueRepository.save(userIssue);
		}
		return existedIssue;
	}
	
	@Override
	public Issue saveIssue(Issue userIssue) {
		return issueRepository.save(userIssue);
	}

	@Override
	public Issue getOneByPid(int pid) {
		return issueRepository.findByProductIdAndProcessStatusNot(pid, ProcessStatus.EVALUATED);
	}

	@Override
	public Issue getOneById(int id) {
		return issueRepository.findById(id).orElse(null);
	}

	@Override
	public List<Issue> getAllByPid(int pid) {
		return issueRepository.findByProductId(pid);
	}

	@Override
	public Issue getOneByCode(String code) {
		return issueRepository.findByCode(code);
	}

	@Override
	public Issue getOneByCustomerId(int customerId) {
		return issueRepository.findByCustomerIdAndProcessStatusNot(customerId, ProcessStatus.EVALUATED);
	}

	@Override
	public List<Issue> getAll() {
		return issueRepository.findAll();
	}

	@Override
	public void deleteIssueAndMRecordById(int id) {
		maintenanceRecordRepository.deleteByIssueId(id);
		issueRepository.deleteById(id);
	}

	@Override
	public List<Issue> getIssueBetween(Date start, Date end) {
		return issueRepository.findByCreateTimeBetween(start, end);
	}
	
	
}
