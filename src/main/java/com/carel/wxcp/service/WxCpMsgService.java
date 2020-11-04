
package com.carel.wxcp.service;

import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.product.Product;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 30, 2020
 */
public interface WxCpMsgService {

	void sendMsgToUser();
	
	void sendMsgToParty(String content, String partyId, Object... params);
	
	void sendMsgToTag();
	
	void sendNewIssueMsg(String content, String partyId, Issue issue, Product product);
}
