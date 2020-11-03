
package com.carel.controller.front;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.carel.controller.BaseController;
import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.main.MaintenanceRecord;

/**
 * Description:
 * 
 * @author Matthew Xu
 * @date Sep 9, 2020
 */
@Controller
public class PhotoController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(PhotoController.class);
	
	@GetMapping("/uissue/{issueId}/{order}")
	public void getIssuePhoto(@PathVariable int issueId, @PathVariable int order, HttpServletResponse resp) {
		try {
			Issue issue = issueService.getOneById(issueId);
			getPhoto(resp, issue, order);
		} catch (Exception e) {
			logger.error("",e);
		}
	}

	@GetMapping("/mrecord/{recordId}/{order}")
	public void getMRecordPhoto(@PathVariable int recordId, @PathVariable int order, HttpServletResponse resp) {
		try {
			MaintenanceRecord record = maintenanceRecordService.getOneById(recordId);
			getPhoto(resp, record, order);
		} catch (Exception e) {
			logger.error("",e);
		}
	}
	
	/**
	 * 
	 * Description:
	 * @param resp
	 * @param object
	 * @param order
	 * @author Matthew Xu
	 * @param <T>
	 * @date Sep 28, 2020
	 */
	private void getPhoto(HttpServletResponse resp, Object object, int order){
		try {
			Class<? extends Object> ojbClass = object.getClass();
			String methodName = "getPhoto" + order;
			Method method = ojbClass.getDeclaredMethod(methodName, new Class[]{});
			byte[] photoData = (byte[])method.invoke(ojbClass.cast(object), new Object[]{});
			if (photoData == null)
				return;

			resp.setContentType("image/png");
			OutputStream os = resp.getOutputStream();
			ByteArrayInputStream is = new ByteArrayInputStream(photoData);
			int len;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf)) != -1) {
				os.write(buf, 0, len);
			}
			os.flush();
		} catch (Exception e) {
			logger.error("",e);
		}
	}
	
}
