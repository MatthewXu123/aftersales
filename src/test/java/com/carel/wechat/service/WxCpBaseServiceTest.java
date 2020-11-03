
package com.carel.wechat.service;

import java.text.MessageFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.carel.property.wxcpmsg.WxCpMsgProperty;

import me.chanjar.weixin.common.error.WxErrorException;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 26, 2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxCpBaseServiceTest {

	@Autowired
	WxCpMsgProperty wxCpMsgProperty;
	
	@Autowired
	Environment env;
	
	/**
	 * Test method for {@link com.carel.wechat.service.WxCpBaseService#getAccessToken(java.lang.String, java.lang.String)}.
	 * @throws WxErrorException 
	 */
	@Test
	public void testGetAccessToken() throws WxErrorException {
	}

}
