
package com.carel.controller.datamanage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.carel.persistence.constant.HumidifierType;
import com.carel.persistence.entity.community.Customer;
import com.carel.persistence.entity.community.Sales;
import com.carel.persistence.entity.product.HumidifierAlarm;
import com.carel.persistence.entity.product.Product;
import com.carel.persistence.entity.product.ProductInfo;

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
	
	private static final String[] params = { "SERIAL NUMBER", "ITEM CODE"};
	
	private static final List<String> HUH_LIST = Arrays.asList("UE","HUT");
	
	private static final List<String> HUT_LIST = Arrays.asList("UR","HUH");
	
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
	
	@PostMapping("/productinfo")
	@ResponseBody
	public JSONObject getProductInfo(@ModelAttribute("filepath")String filepath){
		try {
			InputStream is = UploadController.class.getClassLoader().getResourceAsStream(filepath);
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			List<ProductInfo> productInfoList = new ArrayList<>();
			// We get the data from the second row because the first line is always the headers.
			for(int i = 1; i <= rowNum; i++){
				Row row = sheet.getRow(i);
				String humidifierType = getCellString(row, 0);
				if(row == null || StringUtils.isBlank(humidifierType))
					break;
				ProductInfo newProductInfo = new ProductInfo();
				newProductInfo.setHumidifierType(HumidifierType.valueOf(humidifierType));
				
				ProductInfo productInfo = productInfoService.getOneByType(HumidifierType.valueOf(humidifierType));
				if(productInfo != null)
					newProductInfo.setId(productInfo.getId());
				productInfoList.add(newProductInfo);
			}
			productInfoService.saveAll(productInfoList);
		} catch (EncryptedDocumentException | IOException e) {
			logger.error("",e);
			return resultFactory.getFailResultJSON();
		}
		return resultFactory.getSuccessResultJSON();
	}
	
	@PostMapping("/product")
	@ResponseBody
	public JSONObject getProduct(@ModelAttribute("filepath")String filepath){
		try {
			InputStream is = UploadController.class.getClassLoader().getResourceAsStream(filepath);
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			List<Product> productList = new ArrayList<>();
			
			int[] paramLocation = getParamLocation(sheet.getRow(0));
			int serialNumberLocation = paramLocation[0];
			int itemCodeLocation = paramLocation[1];
			
			// We get the data from the second row because the first line is always the headers.
			for(int i = 1; i <= rowNum; i++){
				Row row = sheet.getRow(i);
				if(row == null)
					break;
				Product newProduct = new Product();
				String serialNumber = getCellString(row, serialNumberLocation);
				String productCode = getCellString(row, itemCodeLocation);
				newProduct.setProductCode(productCode);
				newProduct.setSerialNumber(serialNumber);
				HumidifierType humidifierType = getHumidifierType(productCode);
				if(humidifierType != null)
					newProduct.setProductInfo(productInfoService.getOneByType(humidifierType));
				
//				Product product = productService.getOneBySN(serialNumber);
//				if(product != null)
//					newProduct.setId(product.getId());
				productList.add(newProduct);
			}
			productService.saveAll(productList);
		} catch (EncryptedDocumentException | IOException e) {
			logger.error("",e);
			return resultFactory.getFailResultJSON();
		}
		return resultFactory.getSuccessResultJSON();
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
			for(int i = 1; i <= rowNum; i++){
				Row row = sheet.getRow(i);
				if(row == null)
					break;
				Customer newCustomer = new Customer();
				newCustomer.setPartyName(getCellString(row, 0));
				String customerCode = getCellString(row, 1);
				newCustomer.setCode(customerCode);
				newCustomer.setCustomerCategory(CustomerCategory.valueOf(getCellString(row, 2)));
				newCustomer.setIsOwnerCustomer(Double.valueOf(getCellString(row, 3)).intValue() == 1);
				newCustomer.setWxcpDeptId(StringUtils.isBlank(getCellString(row, 4)) ? "" : String.valueOf(Double.valueOf(getCellString(row, 4)).intValue()));
				newCustomer.setLoginCode(StringUtils.isBlank(getCellString(row, 5)) ? "" : String.valueOf(Double.valueOf(getCellString(row,5)).intValue()));
				newCustomer.setIsShownPolicy(StringUtils.isNotBlank(getCellString(row, 6)) ? Boolean.valueOf(getCellString(row, 6)) : false);
				
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
			for(int i = 1; i <= rowNum; i++){
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
			for(int i = 1; i <= rowNum; i++){
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
	
	@PostMapping("/halarms_huh")
	@ResponseBody
	public JSONObject getHalarmsHUH(@ModelAttribute("filepath")String filepath){
		return getHAlarmsSaved(filepath, HumidifierType.HUH);
	}
	
	@PostMapping("/halarms_hut")
	@ResponseBody
	public JSONObject getHalarmsHUT(@ModelAttribute("filepath")String filepath){
		return getHAlarmsSaved(filepath, HumidifierType.HUT);
	}
	
	/**
	 * 
	 * Description:
	 * @param filePath
	 * @param humidifierType
	 * @return
	 * @author Matthew Xu
	 * @date Nov 3, 2020
	 */
	public JSONObject getHAlarmsSaved(String filePath, HumidifierType humidifierType){
		try {
			InputStream is = UploadController.class.getClassLoader().getResourceAsStream(filePath);
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			List<HumidifierAlarm> list = new ArrayList<>();
			ProductInfo productInfo = productInfoService.getOneByType(humidifierType);
			for(int i = 1; i <= rowNum; i++){
				Row row = sheet.getRow(i);
				if(row == null)
					break;
				String description = getCellString(row, 0);
				String secDescription = getCellString(row, 1);
				String alarmCode = getCellString(row, 2);
				
				HumidifierAlarm humidifierAlarm = new HumidifierAlarm();
				humidifierAlarm.setDescription(description);
				humidifierAlarm.setSecDescription(secDescription);
				humidifierAlarm.setCode(alarmCode);
				humidifierAlarm.setHumidifierType(humidifierType);
				humidifierAlarm.setProductInfo(productInfo);
				if(StringUtils.isNotBlank(alarmCode)){
					HumidifierAlarm oldOne = humidifierAlarmService.getOneByCodeAndType(alarmCode, humidifierType);
					if(oldOne != null){
						humidifierAlarm.setId(oldOne.getId());
					}
				}
				list.add(humidifierAlarm);
			}	
			humidifierAlarmService.saveAll(list);
		} catch (Exception e) {
			return resultFactory.getFailResultJSON();
		}
		return resultFactory.getSuccessResultJSON();
	}
	
	public int[] getParamLocation(Row row) {
		int[] paramsLocation = new int[params.length];
		int k = 0;
		for (int i = 0; i < params.length; i++) {
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				if (params[i].equalsIgnoreCase(row.getCell(j).toString())) {
					paramsLocation[k++] = j;
				}
			}
		}
		return paramsLocation;
	}
	
	private String getCellString(Row row, int cellnum){
		Cell cell = row.getCell(cellnum);
		return cell == null ? "" : cell.toString();
	}
	
	private HumidifierType getHumidifierType(String productCode){
		if(productCode.startsWith(HumidifierType.HUH.getDescription()))
			return HumidifierType.HUH;
		if(productCode.startsWith(HumidifierType.HUT.getDescription()))
			return HumidifierType.HUT;
		if(productCode.startsWith(HumidifierType.UR.getDescription()))
			return HumidifierType.UR;
		if(productCode.startsWith(HumidifierType.UE.getDescription()))
			return HumidifierType.UE;
		return HumidifierType.OTHER;
	}
}
