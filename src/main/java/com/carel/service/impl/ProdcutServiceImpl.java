
package com.carel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.entity.product.Product;
import com.carel.repository.ProductRepository;
import com.carel.service.ProductService;

/**
 * Description:
 * @author Matthew Xu
 * @date Aug 26, 2020
 */
@Service
public class ProdcutServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product getOneBySN(String sn) {
		return productRepository.findBySerialNumber(sn);
	}

	@Override
	public Product getOneById(int pid) {
		return productRepository.findById(pid).get();
	}

	@Override
	public Product getOneBySNAndPCode(String sn, String pcode) {
		return productRepository.findBySerialNumberAndProductInfoProductCode(sn, pcode);
	}

	@Override
	public Product saveOne(Product product) {
		return productRepository.save(product);
	}
	
	

}
