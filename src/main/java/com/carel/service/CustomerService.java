
package com.carel.service;

import java.util.Collection;
import java.util.List;

import com.carel.persistence.entity.community.Customer;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 9, 2020
 */
public interface CustomerService {

	Customer getOneByLoginCode(String loginCode);
	
	Customer getOneByCode(String code);
	
	Customer getOneById(int id);
	
	Customer saveOne(Customer customer);
	
	List<Customer> saveAll(Collection<Customer> customers);
	
	List<Customer> getAll();
	
}
