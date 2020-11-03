
package com.carel.service;

import com.carel.persistence.entity.main.InstallationInfo;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 21, 2020
 */
public interface InstallationInfoService {

	InstallationInfo getOneByPid(Integer pid);
	
	InstallationInfo saveOne(InstallationInfo installationInfo);
}
