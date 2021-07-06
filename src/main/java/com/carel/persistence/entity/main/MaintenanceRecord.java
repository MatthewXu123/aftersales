package com.carel.persistence.entity.main;

import java.io.Serializable;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import com.carel.persistence.constant.ProcessStatus;
import com.carel.persistence.entity.product.HumidifierAlarm;
import com.carel.persistence.entity.product.Product;
import com.carel.persistence.jpa.postgres.arrays.PostgresStringArray;

/**
 * Description:
 * @author Matthew Xu
 * @date Jun 15, 2020
 */
@Entity
@TypeDef(name="stringArray", typeClass=PostgresStringArray.class)
public class MaintenanceRecord implements Serializable{
	
	private static final long serialVersionUID = -9123338683601823466L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@ManyToOne
	@JoinColumn(name = "pid", referencedColumnName = "id")
	private Product product;
	
	@OneToOne
	@JoinColumn(name = "issue_id", referencedColumnName = "id")
	private Issue issue;
	
	private String maintainerName;
	
	private String maintainerPhone;
	
	@ManyToOne
	@JoinColumn(name = "halarm_id", referencedColumnName = "id")
	private HumidifierAlarm hAlarm;
	
	private byte[] photo1;
	
	private byte[] photo2;
	
	private byte[] photo3;
	
	@Enumerated(EnumType.STRING)
	private ProcessStatus processStatus;
	
	@Type(type = "stringArray")
	private String[] partlistReplacement;
	
	@Type(type = "text")
	private String comment;
	
    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
	
	public MaintenanceRecord() {
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

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public String getMaintainerName() {
		return maintainerName;
	}

	public void setMaintainerName(String maintainerName) {
		this.maintainerName = maintainerName;
	}

	public String getMaintainerPhone() {
		return maintainerPhone;
	}

	public void setMaintainerPhone(String maintainerPhone) {
		this.maintainerPhone = maintainerPhone;
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

	public ProcessStatus getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(ProcessStatus processStatus) {
		this.processStatus = processStatus;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String[] getPartlistReplacement() {
		return partlistReplacement;
	}

	public void setPartlistReplacement(String[] partlistReplacement) {
		this.partlistReplacement = partlistReplacement;
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

}
