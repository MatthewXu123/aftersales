
package com.carel.service;

import com.carel.persistence.entity.community.Distributor;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 21, 2020
 */
public interface DistributorService {

	Distributor getOneByLoginCode(String loginCode);
}
