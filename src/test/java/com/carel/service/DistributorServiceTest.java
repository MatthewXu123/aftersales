
package com.carel.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 21, 2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributorServiceTest {

	@Autowired
	DistributorService distributorService;
	
	/**
	 * Test method for {@link com.carel.service.DistributorService#getOneByLoginCode(java.lang.String)}.
	 */
	@Test
	public void testGetOneByLoginCode() {
		assertTrue(distributorService.getOneByLoginCode("distributor").getLoginCode().equals("distributor"));
	}

}
