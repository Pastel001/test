package com.neu.jinshen.dto;

import java.io.Serializable;
import java.sql.Date;

public class Contract implements Serializable{

	// Fields

	private Integer conId;
	private String conName;
	private String conOne;
	private String conTwo;
	private String conThree;
	private Date conDate;
	private Double conFund;
	private String conRemark;
	private Project project;
	private Buildcontent buildcontent;

	// Constructors

	/** default constructor */
	public Contract() {
	}

	/** full constructor */
	public Contract(Integer conId, String conName, String conOne,
			String conTwo, String conThree, Date conDate, Double conFund,
			String conRemark, Project project, Buildcontent buildcontent) {
		super();
		this.conId = conId;
		this.conName = conName;
		this.conOne = conOne;
		this.conTwo = conTwo;
		this.conThree = conThree;
		this.conDate = conDate;
		this.conFund = conFund;
		this.conRemark = conRemark;
		this.project = project;
		this.buildcontent = buildcontent;
	}

	public Contract(String conName, String conOne, String conTwo,
			String conThree, Date conDate, Double conFund, String conRemark,
			Project project, Buildcontent buildcontent) {
		super();
		this.conName = conName;
		this.conOne = conOne;
		this.conTwo = conTwo;
		this.conThree = conThree;
		this.conDate = conDate;
		this.conFund = conFund;
		this.conRemark = conRemark;
		this.project = project;
		this.buildcontent = buildcontent;
	}

	// Property accessors
	public Integer getConId() {
		return conId;
	}

	public void setConId(Integer conId) {
		this.conId = conId;
	}

	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public String getConOne() {
		return conOne;
	}

	public void setConOne(String conOne) {
		this.conOne = conOne;
	}

	public String getConTwo() {
		return conTwo;
	}

	public void setConTwo(String conTwo) {
		this.conTwo = conTwo;
	}

	public String getConThree() {
		return conThree;
	}

	public void setConThree(String conThree) {
		this.conThree = conThree;
	}

	public Date getConDate() {
		return conDate;
	}

	public void setConDate(Date conDate) {
		this.conDate = conDate;
	}

	public Double getConFund() {
		return conFund;
	}

	public void setConFund(Double conFund) {
		this.conFund = conFund;
	}

	public String getConRemark() {
		return conRemark;
	}

	public void setConRemark(String conRemark) {
		this.conRemark = conRemark;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Buildcontent getBuildcontent() {
		return buildcontent;
	}

	public void setBuildcontent(Buildcontent buildcontent) {
		this.buildcontent = buildcontent;
	}

}