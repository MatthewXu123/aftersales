
package com.carel.persistence.entity.main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.carel.persistence.constant.EvaluationLevel;
import com.carel.persistence.constant.ProcessStatus;
import com.carel.persistence.entity.community.Customer;
import com.carel.persistence.entity.product.HumidifierAlarm;
import com.carel.persistence.entity.product.Product;
import com.carel.util.DateUtil;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 10, 2020
 */
@Entity
public class Issue{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@ManyToOne
	@JoinColumn(name = "pid", referencedColumnName = "id")
	private Product product;

	@OneToOne
	@JoinColumn(name="repair_customer_id", referencedColumnName = "id")
	private Customer customer;
	
	@OneToOne(mappedBy = "issue")
	private MaintenanceRecord maintenanceRecord;

	private String code;
	
	@NotBlank(message = "Not null")
	private String username;
	
	private String userPhone;
	
	@ManyToOne
	@JoinColumn(name = "halarm_id", referencedColumnName = "id")
	private HumidifierAlarm hAlarm;
	
	private byte[] photo1;
	
	private byte[] photo2;
	
	private byte[] photo3;
	
	private String comment;
	
	@Enumerated(EnumType.STRING)
	private ProcessStatus processStatus;
	
	@Enumerated(EnumType.STRING)
	private EvaluationLevel evaluationLevel;
	
	private String evaluationComment;
	
    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
	
	public Issue() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public MaintenanceRecord getMaintenanceRecord() {
		return maintenanceRecord;
	}

	public void setMaintenanceRecord(MaintenanceRecord maintenanceRecord) {
		this.maintenanceRecord = maintenanceRecord;
	}

	public String getCode() {
		if (StringUtils.isNotBlank(this.code))
			return this.code;
		return this.product.getSerialNumber() + DateUtil.format(new Date(), DateUtil.CUSTOMED_DATE_FORMET);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public HumidifierAlarm gethAlarm() {
		return hAlarm;
	}

	public void sethAlarm(HumidifierAlarm hAlarm) {
		this.hAlarm = hAlarm;
	}

	public byte[] getPhoto1() {
		return photo1;
	}

	public void setPhoto1(byte[] photo1) {
		this.photo1 = photo1;
	}

	public byte[] getPhoto2() {
		return photo2;
	}

	public void setPhoto2(byte[] photo2) {
		this.photo2 = photo2;
	}

	public byte[] getPhoto3() {
		return photo3;
	}

	public void setPhoto3(byte[] photo3) {
		this.photo3 = photo3;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ProcessStatus getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(ProcessStatus processStatus) {
		this.processStatus = processStatus;
	}

	public EvaluationLevel getEvaluationLevel() {
		return evaluationLevel;
	}

	public void setEvaluationLevel(EvaluationLevel evaluationLevel) {
		this.evaluationLevel = evaluationLevel;
	}

	public String getEvaluationComment() {
		return evaluationComment;
	}

	public void setEvaluationComment(String evaluationComment) {
		this.evaluationComment = evaluationComment;
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

	@Override
	public String toString() {
		return "用户名：" + username +
				"；\n联系方式：" + userPhone + 
				"；\n安装地点：" + product.getInstallationInfo().getAddress() + 
				"；\n故障原因：" + (hAlarm != null ? (hAlarm.getCode() + "-" + hAlarm.getSecDescription()) : "") + 
				"；\n其他：" + comment;
	}

}
