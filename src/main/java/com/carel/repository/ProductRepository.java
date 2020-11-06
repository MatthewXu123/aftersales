package com.carel.repository;

import java.util.List;

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

	List<Product> findBySerialNumber(String serialNumber);
	
	//@Query(value ="SELECT * FROM product WHERE serial_number = ? AND owner_customer_id IS NOT NULL", nativeQuery = true)
	Product findBySerialNumberAndOwnerCustomerNotNull(String sn);
	
	Product findBySerialNumberAndProductCode(String serialNumber, String productCode);
}
