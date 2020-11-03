
package com.carel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.entity.main.InstallationInfo;
import com.carel.repository.InstallationInfoRepository;
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
	
	@Override
	public InstallationInfo getOneByPid(Integer pid) {
		return installationInfoRepository.findByProductId(pid);
	}

	@Override
	public InstallationInfo saveOne(InstallationInfo installationInfo) {
		return installationInfoRepository.save(installationInfo);
	}

}
