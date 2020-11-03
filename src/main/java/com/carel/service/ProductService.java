
package com.carel.service;

import org.springframework.stereotype.Service;

import com.carel.persistence.entity.product.Product;

/**
 * Description:
 * @author Matthew Xu
 * @date Aug 26, 2020
 */
@Service
public interface ProductService {

	Product getOneById(int pid);
	
	Product getOneBySN(String sn);
	
	Product getOneBySNAndPCode(String sn, String pcode);
	
}
