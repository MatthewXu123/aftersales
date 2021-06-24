
package com.carel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carel.persistence.entity.pk.SparePartPK;
import com.carel.persistence.entity.product.SparePart;

/**
 * Description:
 * @author Matthew Xu
 * @date Jun 23, 2021
 */
@Repository
public interface SparePartRepository extends JpaRepository<SparePart, SparePartPK>{

	SparePart findByPkProductCodeAndPkPartCode(String productCode, String partCode);
}
