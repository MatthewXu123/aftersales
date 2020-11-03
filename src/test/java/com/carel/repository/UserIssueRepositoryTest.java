
package com.carel.repository;

import static org.junit.Assert.*;

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
	public void testFindByProduct() {
		Product product = new Product();
		product.setId(1);
		Issue userIssue = userIssueRepository.findByProduct(product);
		assertTrue(userIssue.getId() == 1);
	}
	
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
	public void testFindByIdAndProcessStatus(){
		Issue userIssue = userIssueRepository.findByIdAndProcessStatus(1, ProcessStatus.PENDING);
		assertTrue(userIssue.getId() == 1);
	}

}
