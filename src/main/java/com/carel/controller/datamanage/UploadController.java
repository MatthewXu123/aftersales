
package com.carel.controller.datamanage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.carel.controller.BaseController;
import com.carel.persistence.constant.CustomerCategory;
import com.carel.persistence.entity.community.Customer;
import com.carel.persistence.entity.community.Sales;

/**
 * Description:
 * 
 * @author Matthew Xu
 * @date Sep 2, 2020
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(UploadController.class);

	private static final String TMP_PATH = "WEB-INF/classes/data";

	@ModelAttribute
	public void getFile(@RequestParam MultipartFile file, Model model) {
		try {
			if (file == null || file.getBytes().length == 0)
				return;
			String path = servletContext.getRealPath(TMP_PATH);
			File tmp = new File(path, file.getOriginalFilename());
			if (!tmp.getParentFile().exists()){
				tmp.getParentFile().mkdirs();
			}
			file.transferTo(tmp);
			model.addAttribute("filepath", "data/" + file.getOriginalFilename());
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@PostMapping("/customer")
	@ResponseBody
	public JSONObject getCustomer(@ModelAttribute("filepath")String filepath){
		try {
			InputStream is = UploadController.class.getClassLoader().getResourceAsStream(filepath);
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			List<Customer> customerList = new ArrayList<>();
			// We get the data from the second row because the first line is always the headers.
			for(int i = 1; i < rowNum; i++){
				Row row = sheet.getRow(i);
				if(row == null)
					break;
				Customer newCustomer = new Customer();
				newCustomer.setPartyName(getCellString(row, 0));
				String customerCode = getCellString(row, 1);
				newCustomer.setCode(customerCode);
				newCustomer.setCustomerCategory(CustomerCategory.valueOf(getCellString(row, 2)));
				
				Customer customer = customerService.getOneByCode(customerCode);
				if(customer != null)
					newCustomer.setId(customer.getId());
				customerList.add(newCustomer);
			}
			customerService.saveAll(customerList);
		} catch (EncryptedDocumentException | IOException e) {
			logger.error("",e);
			return resultFactory.getFailResultJSON();
		}
		return resultFactory.getSuccessResultJSON();
	}

	@PostMapping("/sales")
	@ResponseBody
	public JSONObject getSales(@ModelAttribute("filepath")String filepath){
		try {
			InputStream is = UploadController.class.getClassLoader().getResourceAsStream(filepath);
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			List<Sales> salesList = new ArrayList<>();
			// We get the data from the second row because the first line is always the headers.
			for(int i = 1; i < rowNum; i++){
				Row row = sheet.getRow(i);
				if(row == null)
					break;
				Sales newSales = new Sales();
				String salesName = getCellString(row, 0);
				newSales.setSalesName(salesName);
				newSales.setPhone(getCellString(row, 1));
				newSales.setEmail(getCellString(row, 2));
				newSales.setBu(getCellString(row, 3));
				newSales.setRole(getCellString(row, 4));
				newSales.setTerritory(getCellString(row, 5));
				
				Sales sales = salesService.getOneBySalesName(salesName);
				if(sales != null)
					newSales.setId(sales.getId());
				salesList.add(newSales);
			}
			salesService.saveAll(salesList);
		} catch (EncryptedDocumentException | IOException e) {
			logger.error("",e);
			return resultFactory.getFailResultJSON();
		}
		return resultFactory.getSuccessResultJSON();
	}
	
	@PostMapping("/sales_customer")
	@ResponseBody
	public JSONObject getCustomerSales(@ModelAttribute("filepath")String filepath){
		try {
			InputStream is = UploadController.class.getClassLoader().getResourceAsStream(filepath);
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			// We get the data from the second row because the first line is always the headers.
			// CustomerCode, Customer
			Map<String, Customer> customerMap = new HashMap<>();
			// SalesName, Sales
			Map<String, Sales> salesMap = new HashMap<>();
			for(int i = 1; i < rowNum; i++){
				Row row = sheet.getRow(i);
				if(row == null)
					break;
				String salesName = getCellString(row, 0);
				String customerCode = getCellString(row, 1);
				Sales sales = salesMap.get(salesName);
				Customer customer = customerMap.get(customerCode);
				if(sales == null){
					sales = salesService.getOneBySalesName(salesName);
					if(sales != null)
						salesMap.put(salesName, sales);
					else{
						return resultFactory.getFailResultJSON("The sales doesn't exist", salesMap);
					}
						
				}
				// If the customerMap doesn't have the customer...
				if(customer == null){
					customer = customerService.getOneByCode(customerCode);
					if(customer != null)
						customerMap.put(customerCode, customer);
					else{
						return resultFactory.getFailResultJSON("The customer doesn't exist", customerCode);
					}
						
				}
				List<Sales> salesList = customer.getSalesList();
				List<Customer> customerList = sales.getCustomerList();
				if(!salesList.contains(sales)){
					salesList.add(sales);
					customer.setSalesList(salesList);
				}
				if(!customerList.contains(customer)){
					customerList.add(customer);
					sales.setCustomerList(customerList);
				}
				
			}
			customerService.saveAll(customerMap.values());
			salesService.saveAll(salesMap.values());
		} catch (EncryptedDocumentException | IOException e) {
			logger.error("",e);
			return resultFactory.getFailResultJSON();
		}
		return resultFactory.getSuccessResultJSON();
	}
	
	
	private String getCellString(Row row, int cellnum){
		Cell cell = row.getCell(cellnum);
		return cell == null ? "" : cell.toString();
	}
}
