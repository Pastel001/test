package com.neu.jinshen.dto;

import java.io.Serializable;

/**
 * Buildcontent entity. @author MyEclipse Persistence Tools
 */

public class Buildcontent implements Serializable{

	// Fields

	private Integer buildconId;
	private String buildconName;
	private Double buildconFund;

	// Constructors

	/** default constructor */
	public Buildcontent() {
	}

	/** full constructor */
	public Buildcontent(String buildconName, Double buildconFund) {
		this.buildconName = buildconName;
		this.buildconFund = buildconFund;
	}

	public Buildcontent(Integer buildconId, String buildconName,
			Double buildconFund) {
		this.buildconId = buildconId;
		this.buildconName = buildconName;
		this.buildconFund = buildconFund;
	}

	// Property accessors

	public Integer getBuildconId() {
		return this.buildconId;
	}

	public void setBuildconId(Integer buildconId) {
		this.buildconId = buildconId;
	}

	public String getBuildconName() {
		return this.buildconName;
	}

	public void setBuildconName(String buildconName) {
		this.buildconName = buildconName;
	}

	public Double getBuildconFund() {
		return this.buildconFund;
	}

	public void setBuildconFund(Double buildconFund) {
		this.buildconFund = buildconFund;
	}

}