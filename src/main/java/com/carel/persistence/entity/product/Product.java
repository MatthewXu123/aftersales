
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

import com.carel.persistence.entity.community.Customer;
import com.carel.persistence.entity.main.InstallationInfo;
import com.carel.persistence.entity.main.Issue;
import com.carel.persistence.entity.main.MaintenanceRecord;
import com.carel.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import net.bytebuddy.asm.Advice.This;

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
	private ProductInfo productInfo;

	private String serialNumber;

	private String orderNumber;

	private String customerNumber;

	private Date productionDate;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@JsonIgnore
	//private Order order;
	private Customer customer;
	
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<Issue> issues;

	@OneToOne(mappedBy = "product")
	@JsonIgnore
	private InstallationInfo installationInfo;

	@OneToMany(mappedBy = "product")
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	
	public String getInstallationAddress(){
		if (this.installationInfo != null)
			return this.installationInfo.getProvince() + " " 
				+ this.installationInfo.getCity() + " "
				+ this.installationInfo.getDistrict() + " "
				+ this.installationInfo.getStreet() + " "
				+ this.installationInfo.getAddress();
		return "";
	}
}