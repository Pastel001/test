package com.neu.jinshen.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */

public class Project implements Serializable{

	// Fields

	private Integer proId;
	private String proName;
	private String proDept;
	private String proLeader;
	private Double proBudget;
	private Date proBegindate;
	private String proRemark;

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** full constructor */
	public Project(String proName, String proDept, String proLeader,
			Double proBudget, Date proBegindate, String proRemark) {
		this.proName = proName;
		this.proDept = proDept;
		this.proLeader = proLeader;
		this.proBudget = proBudget;
		this.proBegindate = proBegindate;
		this.proRemark = proRemark;
	}

	public Project(Integer proId, String proName, String proDept,
			String proLeader, Double proBudget, Date proBegindate,
			String proRemark) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.proDept = proDept;
		this.proLeader = proLeader;
		this.proBudget = proBudget;
		this.proBegindate = proBegindate;
		this.proRemark = proRemark;
	}

	// Property accessors

	public Integer getProId() {
		return this.proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProDept() {
		return this.proDept;
	}

	public void setProDept(String proDept) {
		this.proDept = proDept;
	}

	public String getProLeader() {
		return this.proLeader;
	}

	public void setProLeader(String proLeader) {
		this.proLeader = proLeader;
	}

	public Double getProBudget() {
		return this.proBudget;
	}

	public void setProBudget(Double proBudget) {
		this.proBudget = proBudget;
	}

	public Date getProBegindate() {
		return this.proBegindate;
	}

	public void setProBegindate(Date proBegindate) {
		this.proBegindate = proBegindate;
	}

	public String getProRemark() {
		return this.proRemark;
	}

	public void setProRemark(String proRemark) {
		this.proRemark = proRemark;
	}

}