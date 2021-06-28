
package com.carel.service;

import java.util.Collection;
import java.util.List;

import com.carel.persistence.entity.product.SparePart;

/**
 * Description:
 * @author Matthew Xu
 * @date Jun 23, 2021
 */
public interface SparePartService {

	public SparePart saveOne(SparePart sparePart);
	
	public void saveAll(Collection<SparePart> spareParts);
	
	List<SparePart> getAll();
}
