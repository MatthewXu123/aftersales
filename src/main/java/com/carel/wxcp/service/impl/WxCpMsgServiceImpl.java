package com.carel.wxcp.service.impl;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carel.wxcp.service.WxCpMsgService;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 30, 2020
 */
@Service
public class WxCpMsgServiceImpl implements WxCpMsgService{

	private static final Logger logger = LoggerFactory.getLogger(WxCpMsgServiceImpl.class);
	
	@Autowired
	WxCpService wxCpService;
	
	@Autowired
	WxCpConfigStorage wxCpConfigStorage;
	
	@Override
	public void sendMsgToUser() {
		
	}

	@Override
	public void sendMsgToParty(String content, String partyId, Object... params) {
		String msg = getMsg(content, params);
		try {
			WxCpMessage wxCpMessage = WxCpMessage.TEXT().
					agentId(wxCpConfigStorage.getAgentId()).
					content(msg).
					toParty(partyId).
					build();
			logger.info("",wxCpService.messageSend(wxCpMessage));
		} catch (WxErrorException e) {
			logger.error("",e);
		}
	}

	@Override
	public void sendMsgToTag() {
		
	}

	public String getMsg(String content, Object... params) {
		return MessageFormat.format(content, params);
	}

}
