
package com.carel.service;

import java.util.Collection;
import java.util.List;

import com.carel.persistence.entity.community.Sales;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 10, 2020
 */
public interface SalesService {

	Sales getOneBySalesName(String salesName);
	
	List<Sales> saveAll(Collection<Sales> salesList);
	
	List<Sales> getAll();
}
