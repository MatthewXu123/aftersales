
package com.carel.wxcp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carel.property.wxcpmsg.WxCpMsgProperty;

/**
 * Description:
 * @author Matthew Xu
 * @date Nov 2, 2020
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WxCpMsgServiceTest {

	@Autowired
	WxCpMsgService wxCpMsgService;
	
	@Autowired
	WxCpMsgProperty wxCpMsgProperty;
	
	/**
	 * Test method for {@link com.carel.wxcp.service.WxCpMsgService#sendMsgToParty(java.lang.String, java.lang.String, java.lang.Object[])}.
	 */
	@Test
	public void testSendMsgToParty() {
		wxCpMsgService.sendMsgToParty(wxCpMsgProperty.getNewIssue(), 
				"2869",
				new Object[]{
						"xxxxx",
						"徐先生", 
						"15366203524", 
						"苏州高新区招商依山郡3#1602室",
						"alarmCode",
						null,
						"坏了坏了大大地坏了",
						""
						});
	}

}
