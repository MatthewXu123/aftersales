package com.carel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carel.persistence.entity.product.Product;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 16, 2020
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	Product findBySerialNumber(String serialNumber);
	
	Product findBySerialNumberAndProductInfoProductCode(String serialNumber, String productCode);
}
