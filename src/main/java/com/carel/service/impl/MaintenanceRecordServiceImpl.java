
package com.carel.service.impl;

import java.util.Date;
import java.util.List;

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

	@Override
	public List<MaintenanceRecord> getAll() {
		return maintenanceRecordRepository.findAll();
	}

	@Override
	public void deleteById(int id) {
		maintenanceRecordRepository.deleteById(id);
	}

	@Override
	public List<MaintenanceRecord> getByCreateTimeBetween(Date start, Date end) {
		return maintenanceRecordRepository.findByCreateTimeBetween(start, end);
	}

}
