
package com.carel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carel.persistence.constant.ProcessStatus;
import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.product.Product;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 17, 2020
 */
@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer>{

	Issue findByProduct(Product product);
	
	Issue findByProductIdAndProcessStatusNot(int pid, ProcessStatus processStatus);
	
	Issue findByProductSerialNumberAndProcessStatus(String serialNumber, ProcessStatus processStatus);
	
	Issue findByIdAndProcessStatus(int id, ProcessStatus processStatus);
	
	List<Issue> findByProductId(int pid);
	
	Issue findByCode(String code);
	
}
