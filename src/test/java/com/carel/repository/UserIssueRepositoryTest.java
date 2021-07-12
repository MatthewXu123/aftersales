
package com.carel.repository;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carel.persistence.constant.ProcessStatus;
import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.product.Product;
import com.carel.util.DateUtil;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 4, 2020
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserIssueRepositoryTest {

	@Autowired
	IssueRepository userIssueRepository;
	
	/**
	 * Test method for {@link com.carel.repository.IssueRepository#findByProduct(com.carel.persistence.entity.product.Product)}.
	 */
	@Test
	public void testFindByProductAndProcessStatus(){
		Issue userIssue = userIssueRepository.findByProductIdAndProcessStatusNot(1, ProcessStatus.EVALUATED);
		assertTrue(userIssue.getId() == 1);
	}
	
	@Test
	public void testFindByProductSerialNumberAndProcessStatus(){
		Product product = new Product();
		product.setSerialNumber("serial_number");
		Issue userIssue = userIssueRepository.findByProductSerialNumberAndProcessStatus("serial_number", ProcessStatus.PENDING);
		assertTrue(userIssue.getId() == 1);
	}
	
	@Test
	public void testFindByProductId(){
		List<Issue> issues = userIssueRepository.findByProductId(1);
		for (Issue issue : issues) {
			System.out.println(issue);
		}
		
	}
	
	@Test
	public void testFindByCreateTimeBetween(){
		Date end = new Date();
		Calendar instance = Calendar.getInstance();
		instance.setTime(end);
		instance.set(Calendar.HOUR_OF_DAY, 0);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);
		end = instance.getTime();
		Date start = DateUtil.addDays(end, -7);
		List<Issue> list = userIssueRepository.findByCreateTimeBetween(start, end);
		for (Issue issue : list) {
			System.out.println(issue);
			System.out.println(issue.getMaintenanceRecord());
		}
	}


}
