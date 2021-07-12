
package com.carel.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.main.MaintenanceRecord;
import com.carel.util.DateUtil;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 12, 2021
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MaintenanceRepositoryTest {

	@Autowired
	MaintenanceRecordRepository maintenanceRecordRepository;
	
	@Test
	public void test(){
		Date end = new Date();
		Calendar instance = Calendar.getInstance();
		instance.setTime(end);
		instance.set(Calendar.HOUR_OF_DAY, 0);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);
		end = instance.getTime();
		Date start = DateUtil.addDays(end, -7);
		List<MaintenanceRecord> list = maintenanceRecordRepository.findByCreateTimeBetween(start, end);
		for (MaintenanceRecord mr : list) {
			System.out.println(mr);
			System.out.println(mr.getIssue());
		}
	}
}
