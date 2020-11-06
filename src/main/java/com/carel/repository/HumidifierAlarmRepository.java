
package com.carel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carel.persistence.entity.product.HumidifierAlarm;

/**
 * Description:
 * @author Matthew Xu
 * @date Nov 3, 2020
 */
@Repository
public interface HumidifierAlarmRepository extends JpaRepository<HumidifierAlarm, Integer>{

	HumidifierAlarm findByCode(String alarmCode);
	
	HumidifierAlarm findByCodeAndProductInfoType(String code, String type);
	
	List<HumidifierAlarm> findByProductInfoType(String type);
	
}
