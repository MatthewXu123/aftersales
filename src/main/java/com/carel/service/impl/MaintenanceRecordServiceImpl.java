
package com.carel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.entity.main.MaintenanceRecord;
import com.carel.repository.MaintenanceRecordRepository;
import com.carel.service.MaintenanceRecordService;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 24, 2020
 */
@Service
public class MaintenanceRecordServiceImpl implements MaintenanceRecordService{

	@Autowired
	MaintenanceRecordRepository maintenanceRecordRepository;
	
	@Override
	public MaintenanceRecord getOneByIssueCode(String issueCode) {
		return maintenanceRecordRepository.findByIssueCode(issueCode);
	}

	@Override
	public MaintenanceRecord saveOne(MaintenanceRecord maintenanceRecord) {
		return maintenanceRecordRepository.save(maintenanceRecord);
	}

	@Override
	public MaintenanceRecord getOneById(Integer id) {
		return maintenanceRecordRepository.findById(id).orElse(null);
	}

}
