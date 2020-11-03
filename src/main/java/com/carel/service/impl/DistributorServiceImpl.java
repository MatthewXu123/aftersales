
package com.carel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.entity.community.Distributor;
import com.carel.repository.DistributorRepository;
import com.carel.service.DistributorService;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 21, 2020
 */
@Service
public class DistributorServiceImpl implements DistributorService{

	@Autowired
	DistributorRepository distributorRepository;
	
	@Override
	public Distributor getOneByLoginCode(String loginCode) {
		return distributorRepository.findByLoginCode(loginCode);
	}

}
