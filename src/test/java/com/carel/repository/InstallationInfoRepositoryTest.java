
package com.carel.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carel.persistence.entity.main.InstallationInfo;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 21, 2020
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class InstallationInfoRepositoryTest {

	@Autowired
	InstallationInfoRepository installationInfoRepository;
	
	/**
	 * Test method for {@link com.carel.repository.InstallationInfoRepository#findByProductId(java.lang.Integer)}.
	 */
	@Test
	public void testFindByProductId() {
		InstallationInfo installationInfo = installationInfoRepository.findByProductId(1);
		assertTrue(installationInfo == null);
	}

}
