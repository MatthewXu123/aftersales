
package com.carel.service;

import java.util.List;

import com.carel.persistence.entity.product.HumidifierAlarm;

/**
 * Description:
 * @author Matthew Xu
 * @date Nov 3, 2020
 */
public interface HumidifierAlarmService {

	List<HumidifierAlarm> saveAll(List<HumidifierAlarm> humidifierAlarms);
	
	HumidifierAlarm getOneById(Integer alarmId);
	
	HumidifierAlarm getOneByCode(String alarmCode);
	
	HumidifierAlarm getOneByCodeAndType(String code, String type);
	
	List<HumidifierAlarm> getAllByType(String type);
}
