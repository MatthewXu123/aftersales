
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

	public static final String DEFAULT_CUS_CODE="CAREL";
	
	public static final String DEFAULT_CUS_DESC = "CAREL";

	Customer getOneByLoginCode(String loginCode);
	
	Customer getOneByCode(String code);
	
	Customer getOneById(int id);
	
	Customer getOneByDeptId(String deptId);
	
	Customer saveOne(Customer customer);
	
	List<Customer> saveAll(Collection<Customer> customers);
	
	List<Customer> getAll();
	
	void saveDefaultOne();
	
}
