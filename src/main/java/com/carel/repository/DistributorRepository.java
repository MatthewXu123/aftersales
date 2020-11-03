
package com.carel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carel.persistence.entity.community.Distributor;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 21, 2020
 */
public interface DistributorRepository extends JpaRepository<Distributor, Integer>{
	
	Distributor findByLoginCode(String loginCode);

}
