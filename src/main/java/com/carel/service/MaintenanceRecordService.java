
package com.carel.service;

import java.util.List;

import com.carel.persistence.entity.main.MaintenanceRecord;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 24, 2020
 */
public interface MaintenanceRecordService {

	MaintenanceRecord getOneByIssueCode(String issueCode);
	
	MaintenanceRecord saveOne(MaintenanceRecord maintenanceRecord);
	
	MaintenanceRecord getOneById(Integer id);
	
	List<MaintenanceRecord> getAll();
	
	void deleteById(int id);
}
