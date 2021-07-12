
package com.carel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.carel.persistence.entity.main.MaintenanceRecord;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 24, 2020
 */
@Repository
public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Integer>{

	MaintenanceRecord findByIssueCode(String issueCode);
	
	@Transactional
	void deleteByProductId(int id);
	
	@Transactional
	void deleteByIssueId(int id);
	
	List<MaintenanceRecord> findByCreateTimeBetween(Date start, Date end);
}
