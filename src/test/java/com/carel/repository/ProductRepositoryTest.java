
package com.carel.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carel.persistence.entity.product.Product;


/**
 * Description:
 * @author Matthew Xu
 * @date Jul 14, 2020
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void testFindBySerialNumber() {
		Product product = productRepository.findBySerialNumber("serial_number");
		Assert.assertTrue(product.getSerialNumber().equals("serial_number"));
	}

	@Test
	public void testFindBySerialNumberAndProductInfoProductCode(){
		Product product = productRepository.findBySerialNumberAndProductInfoProductCode("serial_number", "product_code");
		Assert.assertTrue(product.getId() == 1);
	}
	
}
