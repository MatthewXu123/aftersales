
package com.carel.property.wxcpmsg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 27, 2020
 */
@Component
@ConfigurationProperties(prefix = "wxcpmsg")
@PropertySource(value = "classpath:/property/wxcpmsg/wxcpmsg.properties",encoding = "UTF-8")
public class WxCpMsgProperty {

	private String newIssue;
	
	public String getNewIssue() {
		return newIssue;
	}

	public void setNewIssue(String newIssue) {
		this.newIssue = newIssue;
	}

}
