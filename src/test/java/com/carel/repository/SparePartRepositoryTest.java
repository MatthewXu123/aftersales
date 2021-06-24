
package com.carel.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carel.persistence.entity.pk.SparePartPK;
import com.carel.persistence.entity.product.SparePart;

/**
 * Description:
 * @author Matthew Xu
 * @date Jun 24, 2021
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SparePartRepositoryTest {

	@Autowired
	private SparePartRepository sparePartRepository;
	
	@Test
	public void testSaveOne() {
		try {
			
			SparePartPK pk = new SparePartPK();
			pk.setProductCode("HUT001YDCC2");
			pk.setPartCode("KITVC11006");
			
			SparePart sparePart = new SparePart();
			sparePart.setPk(pk);
			sparePart.setDescription("进水阀");
			sparePart.setRequiredNum(1);
			sparePartRepository.save(sparePart);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testFindByPkProductCodeAndPkPartCode(){
		try {
			SparePart sparePart = sparePartRepository.findByPkProductCodeAndPkPartCode("1", "1");
			System.out.println(sparePart);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
