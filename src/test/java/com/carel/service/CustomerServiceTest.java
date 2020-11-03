
package com.carel.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carel.persistence.entity.community.Customer;
import com.carel.repository.SalesRepository;
import com.carel.util.JsonUtil;

/**
 * Description:
 * @author Matthew Xu
 * @date Oct 19, 2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	SalesRepository salesRepository;
	
	@Test
	public void test() {
		try {
			JSONArray array = JsonUtil.listToJsonArray(customerService.getAll());
			List<Customer> parseArray = JSONArray.parseArray(array.toJSONString(), Customer.class);
			for (Customer customer : parseArray) {
				System.out.println(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
