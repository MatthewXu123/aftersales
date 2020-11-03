
package com.carel.wxcp.service;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 30, 2020
 */
public interface WxCpMsgService {

	void sendMsgToUser();
	
	void sendMsgToParty(String content, String partyId, Object... params);
	
	void sendMsgToTag();
}
