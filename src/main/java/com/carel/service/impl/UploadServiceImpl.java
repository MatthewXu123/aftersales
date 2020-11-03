
package com.carel.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carel.service.UploadService;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 9, 2020
 */
@Service
public class UploadServiceImpl implements UploadService{
	
	private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);
	
	@Override
	public Workbook getWorkBook(String filePath) {
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
			return WorkbookFactory.create(inputStream);
		} catch (EncryptedDocumentException | IOException e) {
			logger.error("",e);
		}
		return null;
	}

}
