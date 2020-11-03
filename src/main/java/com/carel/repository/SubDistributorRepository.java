
package com.carel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carel.persistence.entity.community.SubDistributor;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 21, 2020
 */
public interface SubDistributorRepository extends JpaRepository<SubDistributor, Integer>{

	SubDistributor findByLoginCode(String loginCode);
}
