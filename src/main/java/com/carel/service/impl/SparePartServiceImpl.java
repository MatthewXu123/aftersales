
package com.carel.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.entity.product.SparePart;
import com.carel.repository.SparePartRepository;
import com.carel.service.SparePartService;

/**
 * Description:
 * @author Matthew Xu
 * @date Jun 23, 2021
 */
@Service
public class SparePartServiceImpl implements SparePartService{

	@Autowired
	private SparePartRepository sparePartRepository;

	@Override
	public SparePart saveOne(SparePart sparePart) {
		return sparePartRepository.save(sparePart);
	}

	@Override
	public void saveAll(Collection<SparePart> spareParts) {
		sparePartRepository.saveAll(spareParts);
	}

	@Override
	public List<SparePart> getAll() {
		return sparePartRepository.findAll();
	}
	
	
}
