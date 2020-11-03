package com.carel.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.carel.wxcp.persistence.WxCpConfig;

public class JsonUtilTest {

	@Test
	public void testObjectToJsonString() {
		fail("Not yet implemented");
	}

	@Test
	public void testListToJsonArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testObjectToJson() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadFromPath() {
		WxCpConfig wxCpConfig = JsonUtil.getObjectFromReource(WxCpConfig.class, "/config/wxcp.json");
		System.out.println(wxCpConfig);
	}

}
