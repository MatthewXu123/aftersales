
package com.carel.persistence.entity.product;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.alibaba.fastjson.annotation.JSONField;
import com.carel.persistence.entity.community.Customer;
import com.carel.persistence.entity.main.InstallationInfo;
import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.main.MaintenanceRecord;
import com.carel.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Description:
 * 
 * @author Matthew Xu
 * @date Jul 10, 2020
 */
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "pinfo_id", referencedColumnName = "id")
	@JSONField(serialize = false)
	@JsonIgnore
	private ProductInfo productInfo;

	private String serialNumber;
	
	private String productCode;

	private String orderNumber;

	private String customerNumber;

	private Date productionDate;
	
	@ManyToOne
	@JoinColumn(name = "owner_customer_id", referencedColumnName = "id")
	@JSONField(serialize = false)
	@JsonIgnore
	private Customer ownerCustomer;
	
	@OneToMany(mappedBy = "product")
	@JSONField(serialize = false)
	@JsonIgnore
	private List<Issue> issues;

	@OneToOne(mappedBy = "product")
	@JSONField(serialize = false)
	@JsonIgnore
	private InstallationInfo installationInfo;

	@OneToMany(mappedBy = "product")
	@JSONField(serialize = false)
	@JsonIgnore
	private List<MaintenanceRecord> maintenanceRecords;

	@Column(updatable = false)
	@CreationTimestamp
	private Date createTime;

	@UpdateTimestamp
	private Date updateTime;

	public Product() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public InstallationInfo getInstallationInfo() {
		return installationInfo;
	}

	public void setInstallationInfo(InstallationInfo installationInfo) {
		this.installationInfo = installationInfo;
	}
	
//	public Order getOrder() {
//		return order;
//	}
//
//	public void setOrder(Order order) {
//		this.order = order;
//	}
	

	public List<Issue> getIssues() {
		return issues;
	}

	public Customer getOwnerCustomer() {
		return ownerCustomer;
	}

	public void setOwnerCustomer(Customer ownerCustomer) {
		this.ownerCustomer = ownerCustomer;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	
	public List<MaintenanceRecord> getMaintenanceRecords() {
		return maintenanceRecords;
	}

	public void setMaintenanceRecords(List<MaintenanceRecord> maintenanceRecords) {
		this.maintenanceRecords = maintenanceRecords;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * 
	 * Description:
	 * @return
	 * @author Matthew Xu
	 * @date Sep 22, 2020
	 */
	public Date getWarrantyDate(){
		return installationInfo == null ? null : DateUtil.addMonths(this.installationInfo.getCreateTime(), 24);
	}

	/**
	 * 
	 * Description:
	 * @return
	 * @author Matthew Xu
	 * @date Sep 22, 2020
	 */
	public boolean getIsInWarranty(){
		return getWarrantyDate() == null ? false : new Date().before(getWarrantyDate());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		return true;
	}
	
}
