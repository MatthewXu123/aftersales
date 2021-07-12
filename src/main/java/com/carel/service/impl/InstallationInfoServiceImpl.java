
package com.carel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.entity.main.InstallationInfo;
import com.carel.repository.InstallationInfoRepository;
import com.carel.repository.IssueRepository;
import com.carel.repository.MaintenanceRecordRepository;
import com.carel.service.InstallationInfoService;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 21, 2020
 */
@Service
public class InstallationInfoServiceImpl implements InstallationInfoService{

	@Autowired
	InstallationInfoRepository installationInfoRepository;
	
	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	MaintenanceRecordRepository maintenanceRecordRepository;
	
	@Override
	public InstallationInfo getOneByPid(Integer pid) {
		return installationInfoRepository.findByProductId(pid);
	}

	@Override
	public InstallationInfo saveOne(InstallationInfo installationInfo) {
		return installationInfoRepository.save(installationInfo);
	}

	@Override
	public List<InstallationInfo> getAll() {
		return installationInfoRepository.findAll();
	}

	@Override
	public void deleteInstallationInfoAndIssueAndMRecordById(int id) {
		InstallationInfo installationInfo = installationInfoRepository.findById(id).get();
		int pid = installationInfo.getProduct().getId();
		maintenanceRecordRepository.deleteByProductId(pid);
		issueRepository.deleteByProductId(pid);
		installationInfoRepository.deleteByProductId(pid);
	}

	@Override
	public List<InstallationInfo> getByCreateTimeBetween(Date start, Date end) {
		return installationInfoRepository.getByCreateTimeBetween(start, end);
	}

}
