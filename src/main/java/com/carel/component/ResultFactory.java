
package com.carel.component;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.carel.persistence.common.RestResult;
import com.carel.persistence.common.ResultCode;
import com.carel.util.JsonUtil;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 17, 2020
 */
@Component
public class ResultFactory {

	private static final long serialVersionUID = -1469899904991156521L;

	private static final String SUCCESS = "success";
	
	private static final String FAILED = "failed";

	/**
	 * 
	 * Description: Get the default successful result.
	 * @return
	 * @author Matthew Xu
	 * @date 11 Jan 2020
	 */
    public RestResult getSuccessResult() {
    	return getFreeResult(ResultCode.SUCCESS, SUCCESS, null);
    }
    
    /**
	 * 
	 * Description:
	 * @return
	 * @author Matthew Xu
	 * @date Sep 22, 2020
	 */
	public JSONObject getSuccessResultJSON(){
		return JsonUtil.objectToJson(getSuccessResult());
	}
    
    /**
     * 
     * Description: Get the successful result with data.
     * @param data
     * @return
     * @author Matthew Xu
     * @date 11 Jan 2020
     */
    public RestResult getSuccessResult(Object data) {
    	return getFreeResult(ResultCode.SUCCESS, SUCCESS, data);
    }
    
    /**
     * 
     * Description:
     * @param data
     * @return
     * @author Matthew Xu
     * @date 11 Jan 2020
     */
    public JSONObject getSuccessResultJSON(Object data) {
    	return JsonUtil.objectToJson(getFreeResult(ResultCode.SUCCESS, SUCCESS, data));
    }
    
    /**
     * 
     * Description: Get the successful result with data and customized message.
     * @param msg
     * @param data
     * @return
     * @author Matthew Xu
     * @date 11 Jan 2020
     */
    public RestResult getSuccessResult(String msg,Object data) {
    	return getFreeResult(ResultCode.SUCCESS, msg, data);
    }
    
    /**
     * 
     * Description:
     * @param msg
     * @param data
     * @return
     * @author Matthew Xu
     * @date Oct 13, 2020
     */
    public JSONObject getSuccessResultJSON(String msg,Object data) {
    	return JsonUtil.objectToJson(getFreeResult(ResultCode.SUCCESS, msg, data));
    }
   
    /**
     * 
     * Description: Get the default failed result.
     * @return
     * @author Matthew Xu
     * @date 11 Jan 2020
     */
    public RestResult getFailResult() {
    	return getFreeResult(ResultCode.FAIL, FAILED, null);
    }
    
    /**
	 * 
	 * Description:
	 * @return
	 * @author Matthew Xu
	 * @date Sep 22, 2020
	 */
	public JSONObject getFailResultJSON(){
		return JsonUtil.objectToJson(getFailResult());
	}
    
    /**
     * 
     * Description: Get the failed result with customized message.
     * @param msg
     * @return
     * @author Matthew Xu
     * @date 11 Jan 2020
     */
    public RestResult getFailResult(String msg) {
    	return getFreeResult(ResultCode.FAIL, msg, null);
    }
    
    /**
     * 
     * Description:
     * @param msg
     * @return
     * @author Matthew Xu
     * @date Oct 13, 2020
     */
    public JSONObject getFailResultJSON(String msg) {
    	return JsonUtil.objectToJson(getFreeResult(ResultCode.FAIL, msg, null));
    }
    
    /**
     * 
     * Description: Get the failed result with data and customized message.
     * @param msg
     * @param data
     * @return
     * @author Matthew Xu
     * @date 11 Jan 2020
     */
    public RestResult getFailResult(String msg, Object data) {
    	return getFreeResult(ResultCode.FAIL, msg, data);
    }
    
    /**
     * 
     * Description:
     * @param msg
     * @param data
     * @return
     * @author Matthew Xu
     * @date Oct 13, 2020
     */
    public JSONObject getFailResultJSON(String msg, Object data) {
    	return JsonUtil.objectToJson(getFreeResult(ResultCode.FAIL, msg, data));
    }
    
    /**
     * 
     * Description: Get the customized result with the code, the message and the data.
     * @param resultCode
     * @param msg
     * @param data
     * @return
     * @author Matthew Xu
     * @date 11 Jan 2020
     */
    public RestResult getFreeResult(ResultCode resultCode, String msg, Object data) {
    	RestResult restResult = new RestResult();
    	restResult.setStatus(resultCode.getStatus());
    	restResult.setMsg(msg);
    	restResult.setData(data);
        return restResult;
    }
}
