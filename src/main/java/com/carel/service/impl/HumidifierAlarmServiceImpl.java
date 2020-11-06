
package com.carel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.entity.product.HumidifierAlarm;
import com.carel.repository.HumidifierAlarmRepository;
import com.carel.service.HumidifierAlarmService;

/**
 * Description:
 * @author Matthew Xu
 * @date Nov 3, 2020
 */
@Service
public class HumidifierAlarmServiceImpl implements HumidifierAlarmService{

	@Autowired
	HumidifierAlarmRepository humidifierAlarmRepository;
	
	@Override
	public List<HumidifierAlarm> saveAll(List<HumidifierAlarm> humidifierAlarms) {
		return humidifierAlarmRepository.saveAll(humidifierAlarms);
	}

	@Override
	public HumidifierAlarm getOneByCode(String alarmCode) {
		return humidifierAlarmRepository.findByCode(alarmCode);
	}

	@Override
	public List<HumidifierAlarm> getAllByType(String type) {
		return humidifierAlarmRepository.findByProductInfoType(type);
	}

	@Override
	public HumidifierAlarm getOneByCodeAndType(String code, String type) {
		return humidifierAlarmRepository.findByCodeAndProductInfoType(code, type);
	}

	@Override
	public HumidifierAlarm getOneById(Integer alarmId) {
		return humidifierAlarmRepository.findById(alarmId).orElse(null);
	}

}
