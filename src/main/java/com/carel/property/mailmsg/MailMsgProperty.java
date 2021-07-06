
package com.carel.property.mailmsg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 27, 2020
 */
@Component
@ConfigurationProperties(prefix = "mailmsg")
@PropertySource(value = "classpath:/property/mailmsg/mailmsg.properties",encoding = "UTF-8")
public class MailMsgProperty {

	private String badEvaTitle;
	
	private String badEvaContent;

	public String getBadEvaTitle() {
		return badEvaTitle;
	}

	public void setBadEvaTitle(String badEvaTitle) {
		this.badEvaTitle = badEvaTitle;
	}

	public String getBadEvaContent() {
		return badEvaContent;
	}

	public void setBadEvaContent(String badEvaContent) {
		this.badEvaContent = badEvaContent;
	}
	
	
	
}
