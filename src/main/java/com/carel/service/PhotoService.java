
package com.carel.service;

import com.carel.persistence.entity.main.Issue;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 10, 2020
 */
public interface PhotoService {

	byte[] getIssuePhoto(Issue issue, int photoId);
	
	void deleteIssuePhoto(Issue issue, int photoId);
	
	void updateIssuePhoto(Issue issue, int photoId, byte[] photoData);
	
	void replaceOldIssuePhoto(Issue issue, int photoId);
}
