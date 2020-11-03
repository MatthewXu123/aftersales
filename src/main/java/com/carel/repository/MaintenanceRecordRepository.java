
package com.carel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carel.persistence.entity.main.MaintenanceRecord;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 24, 2020
 */
@Repository
public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Integer>{

	MaintenanceRecord findByIssueCode(String issueCode);
}
