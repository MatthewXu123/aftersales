
package com.carel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.constant.ProcessStatus;
import com.carel.persistence.entity.main.Issue;
import com.carel.repository.ProductInfoRepository;
import com.carel.repository.IssueRepository;
import com.carel.service.IssueService;

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

	@Override
	public Issue saveNewPendingIssue(Issue userIssue) {
		userIssue.setProcessStatus(ProcessStatus.PENDING);
		return issueRepository.save(userIssue);
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
	
}