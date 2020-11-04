
package com.carel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carel.persistence.entity.community.Customer;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 17, 2020
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Customer findByLoginCode(String loginCode);
	
	Customer findByCode(String code);
	
	Customer findByWxcpDeptId(int deptId);
}
