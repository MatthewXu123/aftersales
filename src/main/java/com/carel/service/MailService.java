
package com.carel.service;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 6, 2021
 */
public interface MailService {

	public void sendSimpleMail(String to, String subject, String contnet);
	
	public void sendSimpleMail(String to, String[] cc, String subject, String contnet);
	
	public void sendHtmlMail(String to, String subject, String contnet);
	
	public void sendAttachmentsMail(String to, String subject, String contnet, String filePath);
	
	public void sendInlinkResourceMail(String to, String subject, String contnet, String rscPath, String rscId);
}
