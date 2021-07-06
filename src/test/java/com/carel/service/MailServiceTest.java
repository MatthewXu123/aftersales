
package com.carel.service;

import java.text.MessageFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carel.property.mailmsg.MailMsgProperty;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 6, 2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

	@Autowired
	private MailService mailService;
	
	@Autowired
	private MailMsgProperty mailMsgProperty;
	/**
	 * Test method for {@link com.carel.service.MailService#sendSimpleMail(java.lang.String, java.lang.String[], java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSendSimpleMailStringStringArrayStringString() {
		mailService.sendSimpleMail(null, null, mailMsgProperty.getBadEvaTitle()
				, MessageFormat.format(mailMsgProperty.getBadEvaContent(), new Object[]{
						"test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test"
				}));
	}

}
