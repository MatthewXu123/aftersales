
package com.carel.service;

import java.util.List;

import com.carel.persistence.constant.HumidifierType;
import com.carel.persistence.entity.product.ProductInfo;

/**
 * Description:
 * @author Matthew Xu
 * @date Nov 4, 2020
 */
public interface ProductInfoService {

	ProductInfo getOneByType(HumidifierType humidifierType);
	
	ProductInfo saveOne(ProductInfo productInfo);
	
	List<ProductInfo> saveAll(List<ProductInfo> productInfoList);
} 
