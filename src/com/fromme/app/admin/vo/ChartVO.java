package com.fromme.app.admin.vo;

public class ChartVO{
	//기준일
	private String criteriaDate;
	private int dateData;
	
	public ChartVO(String criteriaDate, int dateData) {
		this.criteriaDate = criteriaDate;
		this.dateData = dateData;
	}
	
	public String getCriteriaDate() {
		return criteriaDate;
	}
	public void setCriteriaDate(String criteriaDate) {
		this.criteriaDate = criteriaDate;
	}
	public int getDateData() {
		return dateData;
	}
	public void setDateData(int dateData) {
		this.dateData = dateData;
	}
	
}
