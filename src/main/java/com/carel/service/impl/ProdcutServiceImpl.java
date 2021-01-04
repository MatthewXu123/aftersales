
package com.carel.service.impl;

import java.util.List;

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
		Product product = productRepository.findBySerialNumberAndOwnerCustomerNotNull(sn);
		if(product != null)
			return product;
		List<Product> productList = productRepository.findBySerialNumber(sn);
		if(productList != null && productList.size() != 0)
			return productList.get(0);
		return null;
	}

	@Override
	public Product getOneById(int pid) {
		return productRepository.findById(pid).get();
	}

	@Override
	public Product getOneBySNAndPCode(String sn, String pcode) {
		return productRepository.findBySerialNumberAndProductCode(sn, pcode);
	}

	@Override
	public Product saveOne(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> saveAll(List<Product> productList) {
		return productRepository.saveAll(productList);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

}
