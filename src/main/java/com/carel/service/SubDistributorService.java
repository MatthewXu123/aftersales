
package com.carel.service;

import com.carel.persistence.entity.community.SubDistributor;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 21, 2020
 */
public interface SubDistributorService {

	SubDistributor getOneByLoginCode(String loginCode);
}
