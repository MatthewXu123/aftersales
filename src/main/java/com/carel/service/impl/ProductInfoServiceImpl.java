
package com.carel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.persistence.constant.HumidifierType;
import com.carel.persistence.entity.product.ProductInfo;
import com.carel.repository.ProductInfoRepository;
import com.carel.service.ProductInfoService;

/**
 * Description:
 * @author Matthew Xu
 * @date Nov 4, 2020
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService{

	@Autowired
	ProductInfoRepository productInfoRepository;
	
	@Override
	public ProductInfo getOneByType(HumidifierType humidifierType) {
		return productInfoRepository.findByHumidifierType(humidifierType);
	}

	@Override
	public ProductInfo saveOne(ProductInfo productInfo) {
		return productInfoRepository.save(productInfo);
	}

	@Override
	public List<ProductInfo> saveAll(List<ProductInfo> productInfoList) {
		return productInfoRepository.saveAll(productInfoList);
	}

}
