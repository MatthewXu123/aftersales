
package com.carel.wxcp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carel.util.JsonUtil;
import com.carel.wxcp.persistence.WxCpConfig;

import me.chanjar.weixin.cp.api.WxCpDepartmentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpUserService;
import me.chanjar.weixin.cp.api.impl.WxCpDepartmentServiceImpl;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.api.impl.WxCpUserServiceImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 27, 2020
 */
@Configuration
public class BeanConfig {

	@Bean
	WxCpConfigStorage wxCpConfigStorage(){
		/*WxCpDefaultConfigImpl wxCpConfigStorage = new WxCpDefaultConfigImpl();
		WxCpConfig wxCpConfig = JsonUtil.getObjectFromReource(WxCpConfig.class, "/config/wxcp.json");
		wxCpConfigStorage.setCorpId(wxCpConfig.getCorpId());
		wxCpConfigStorage.setCorpSecret(wxCpConfig.getCorpSecret());
		wxCpConfigStorage.setAgentId(wxCpConfig.getAgentId());*/
		return JsonUtil.getObjectFromReource(WxCpDefaultConfigImpl.class, "/config/wxcp_afs.json");
	}
	
	@Bean
	WxCpService wxCpService(){
		WxCpService wxCpService = new WxCpServiceImpl();
		wxCpService.setWxCpConfigStorage(wxCpConfigStorage());
		return wxCpService;
	}
	
	@Bean
	WxCpUserService wxCpUserService(){
		return new WxCpUserServiceImpl(wxCpService());
	}
	
	@Bean
	WxCpDepartmentService wxCpDepartmentService(){
		return new WxCpDepartmentServiceImpl(wxCpService());
	}
}
