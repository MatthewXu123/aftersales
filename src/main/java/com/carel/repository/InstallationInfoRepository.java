
package com.carel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.carel.persistence.entity.main.InstallationInfo;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 17, 2020
 */
@Repository
public interface InstallationInfoRepository extends JpaRepository<InstallationInfo, Integer>{

	InstallationInfo findByProductId(Integer pid);
	
	@Transactional
	void deleteByProductId(int id);
	
}
