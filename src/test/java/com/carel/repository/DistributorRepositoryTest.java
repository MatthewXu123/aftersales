
package com.carel.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carel.persistence.entity.community.Distributor;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 21, 2020
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DistributorRepositoryTest {

	@Autowired
	DistributorRepository distributorRepository;
	
	/**
	 * Test method for {@link com.carel.repository.DistributorRepository#findByLoginCode(java.lang.String)}.
	 */
	@Test
	public void testFindByLoginCode() {
		Distributor distributor = distributorRepository.findByLoginCode("distributor");
		assertTrue(distributor.getLoginCode().equals("distributor"));
	}

}
