
package com.carel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.carel.persistence.constant.ProcessStatus;
import com.carel.persistence.entity.main.Issue;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 17, 2020
 */
@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer>{

	Issue findByProductIdAndProcessStatusNot(int pid, ProcessStatus processStatus);
	
	Issue findByProductSerialNumberAndProcessStatus(String serialNumber, ProcessStatus processStatus);
	
	Issue findByProductIdAndProcessStatus(int pid, ProcessStatus processStatus);
	
	Issue findByIdAndProcessStatus(int id, ProcessStatus processStatus);
	
	Issue findByCustomerIdAndProcessStatusNot(int customerId, ProcessStatus processStatus);
	
	List<Issue> findByProductId(int pid);
	
	Issue findByCode(String code);
	
	@Transactional
	void deleteByProductId(int id);
	
	List<Issue> findByCreateTimeBetween(Date start, Date end);
	
}
