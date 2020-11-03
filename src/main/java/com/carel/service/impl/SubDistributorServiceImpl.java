
package com.carel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.entity.community.SubDistributor;
import com.carel.repository.SubDistributorRepository;
import com.carel.service.SubDistributorService;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 21, 2020
 */
@Service
public class SubDistributorServiceImpl implements SubDistributorService{

	@Autowired
	SubDistributorRepository subDistributorRepository;
	
	@Override
	public SubDistributor getOneByLoginCode(String loginCode) {
		return subDistributorRepository.findByLoginCode(loginCode);
	}

}
