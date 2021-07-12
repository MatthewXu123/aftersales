
package com.carel.repository;

import java.util.Date;
import java.util.List;

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
	
	List<InstallationInfo> getByCreateTimeBetween(Date start, Date end);
	
}
