
package com.carel.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.entity.community.Sales;
import com.carel.repository.SalesRepository;
import com.carel.service.SalesService;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 10, 2020
 */
@Service
public class SalesServiceImpl implements SalesService{

	@Autowired
	SalesRepository salesRepository;
	
	@Override
	public Sales getOneBySalesName(String salesName) {
		return salesRepository.findBySalesName(salesName);
	}

	@Override
	public List<Sales> saveAll(Collection<Sales> salesList) {
		return salesRepository.saveAll(salesList);
	}

	@Override
	public List<Sales> getAll() {
		return salesRepository.findAll();
	}

}
