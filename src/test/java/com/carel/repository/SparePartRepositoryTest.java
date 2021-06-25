
package com.carel.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carel.persistence.entity.pk.SparePartPK;
import com.carel.persistence.entity.product.SparePart;

/**
 * Description:
 * @author Matthew Xu
 * @date Jun 24, 2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest
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
			sparePartRepository.save(sparePart);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testSaveAll(){
		try {
			SparePartPK pk = new SparePartPK();
			pk.setProductCode("HUT001YDCC3");
			pk.setPartCode("KITVC11007");
			
			SparePart sparePart = new SparePart();
			sparePart.setPk(pk);
			sparePart.setDescription("进水阀2");
			
			SparePartPK pk2 = new SparePartPK();
			pk2.setProductCode("HUT001YDCC3");
			pk2.setPartCode("KITVC11006");
			
			SparePart sparePart2 = new SparePart();
			sparePart2.setPk(pk2);
			sparePart2.setDescription("进水阀3");
			
			
			List<SparePart> list = new ArrayList<>();
			list.add(sparePart);
			list.add(sparePart2);
			
			sparePartRepository.saveAll(list);
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
