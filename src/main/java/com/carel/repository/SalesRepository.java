
package com.carel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carel.persistence.entity.community.Sales;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 17, 2020
 */
@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer>{

	Sales findBySalesName(String salesName);
}
