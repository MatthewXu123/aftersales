
package com.carel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carel.persistence.constant.HumidifierType;
import com.carel.persistence.entity.product.ProductInfo;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 16, 2020
 */
@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

	ProductInfo findByHumidifierType(HumidifierType humidifierType);
}
