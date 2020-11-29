package com.fromme.app.order;


public class OrdererInfoVO {
	private int orderer_info_no;
	private String orderer_name; 
	private String orderer_phone;  
	private String orderer_email;
	private int total_price;
	
	
	
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public int getOrderer_info_no() {
		return orderer_info_no;
	}
	public void setOrderer_info_no(int orderer_info_no) {
		this.orderer_info_no = orderer_info_no;
	}
	public String getOrderer_name() {
		return orderer_name;
	}
	public void setOrderer_name(String orderer_name) {
		this.orderer_name = orderer_name;
	}
	public String getOrderer_phone() {
		return orderer_phone;
	}
	public void setOrderer_phone(String orderer_phone) {
		this.orderer_phone = orderer_phone;
	}
	public String getOrderer_email() {
		return orderer_email;
	}
	public void setOrderer_email(String orderer_email) {
		this.orderer_email = orderer_email;
	}
	
}
