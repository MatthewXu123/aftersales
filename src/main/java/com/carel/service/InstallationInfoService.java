
package com.carel.service;

import java.util.Date;
import java.util.List;

import com.carel.persistence.entity.main.InstallationInfo;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 21, 2020
 */
public interface InstallationInfoService {

	InstallationInfo getOneByPid(Integer pid);
	
	InstallationInfo saveOne(InstallationInfo installationInfo);
	
	List<InstallationInfo> getAll();
	
	void deleteInstallationInfoAndIssueAndMRecordById(int id);
	
	List<InstallationInfo> getByCreateTimeBetween(Date start, Date end);
}
