
package com.carel.service;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 9, 2020
 */
public interface UploadService {

	Workbook getWorkBook(String filePath);
}
