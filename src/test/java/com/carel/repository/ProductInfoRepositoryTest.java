
package com.carel.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.carel.persistence.entity.product.ProductInfo;

/**
 * Description:
 * @author Matthew Xu
 * @date Aug 24, 2020
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductInfoRepositoryTest {

	@Autowired
	private ProductInfoRepository productInfoRepository;
	
	@Test
	@Rollback(value = false)
	public void testSaveOne() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setDescription("huh");
		productInfo.setType("huh");
		productInfoRepository.save(productInfo);
	}
	
	
}
