
package com.carel.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 17, 2020
 */
public class JsonUtil {

	/**
	 * 
	 * @param object
	 * @return
	 * @author MatthewXu
	 * @date Sep 8, 2019
	 */
	public static String objectToJsonString(Object object){
		return JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 * @author MatthewXu
	 * @date Sep 8, 2019
	 */
	public static <E> JSONArray listToJsonArray(List<E> list){
		return JSONArray.parseArray(objectToJsonString(list));
	}
	
	/**
	 * @param object
	 * @return
	 * @author MatthewXu
	 * @date Nov 26, 2019
	 */
	public static JSONObject objectToJson(Object object) {
		return (JSONObject) JSONObject.toJSON(object);
	}
	
	public static <T> T getObjectFromReource(Class<T> clazz, String filePath){
		return JSONObject.parseObject(getJsonStringFromResource(filePath), clazz);
	}
	
	public static String getJsonStringFromResource(String path){
    	StringBuilder result = new StringBuilder();
    	try {
    		InputStream is = JsonUtil.class.getResourceAsStream(path);
    		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			//InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader breader = new BufferedReader(isr);
			String line;
			while((line = breader.readLine()) != null){
				result.append(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return result.toString();
    }
	
}
