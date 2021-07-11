
package com.carel.service;

import java.util.List;

import com.carel.persistence.entity.main.Issue;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 2, 2020
 */
public interface IssueService {

	Issue saveNewPendingIssue(Issue issue);
	
	Issue saveIssue(Issue issue);
	
	Issue getOneByPid(int pid);
	
	Issue getOneByCustomerId(int customerId);
	
	List<Issue> getAllByPid(int pid);
	
	List<Issue> getAll();
	
	Issue getOneById(int id);
	
	Issue getOneByCode(String code);
	
	void deleteIssueAndMRecordById(int id);
}
