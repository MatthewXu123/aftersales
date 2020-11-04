
package com.carel.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.entity.community.Customer;
import com.carel.repository.CustomerRepository;
import com.carel.service.CustomerService;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 9, 2020
 */
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer getOneByLoginCode(String loginCode) {
		return customerRepository.findByLoginCode(loginCode);
	}

	@Override
	public Customer getOneByCode(String code) {
		return customerRepository.findByCode(code);
	}

	@Override
	public Customer saveOne(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> saveAll(Collection<Customer> customers) {
		return customerRepository.saveAll(customers);
	}

	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getOneById(int id) {
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	public Customer getOneByDeptId(String deptId) {
		return customerRepository.findByWxcpDeptId(deptId);
	}

}
