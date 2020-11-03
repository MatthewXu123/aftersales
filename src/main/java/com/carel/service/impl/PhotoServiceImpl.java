
package com.carel.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

import com.carel.persistence.entity.main.Issue;
import com.carel.service.PhotoService;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 10, 2020
 */
@Service
public class PhotoServiceImpl implements PhotoService{

	@Override
	public byte[] getIssuePhoto(Issue issue, int photoId) {
		
		try {
			String methodName = "getPhoto" + photoId;
			Method method = Issue.class.getDeclaredMethod(methodName, new Class[]{});
			return (byte[]) method.invoke(issue, new Object[]{});
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void deleteIssuePhoto(Issue issue, int photoId) {
		
	}

	@Override
	public void updateIssuePhoto(Issue issue, int photoId, byte[] photoData) {
		try {
			String methodName = "setPhoto" + photoId;
			Method method = Issue.class.getDeclaredMethod(methodName, new Class[]{byte[].class});
			method.invoke(issue, new Object[]{photoData});
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void replaceOldIssuePhoto(Issue issue, int photoId) {
		updateIssuePhoto(issue, photoId, getIssuePhoto(issue, photoId));
	}

	

}
