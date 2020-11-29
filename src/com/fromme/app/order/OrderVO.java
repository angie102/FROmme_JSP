package com.fromme.app.order;

import java.util.Date;

public class OrderVO {
	private int classes_no;
	private String users_id;
	private String orderlist_date;
	private String payment_date;
	private int orderlist_state_no;
	private int orderlist_no;
	private int studio_no;
	private String choice_date;
	private int classes_info_no;
	private int reservation_no;
	private int quantity;
	private int orderer_info_no;

	
	public int getOrderer_info_no() {
		return orderer_info_no;
	}
	public void setOrderer_info_no(int orderer_info_no) {
		this.orderer_info_no = orderer_info_no;
	}
	public int getReservation_no() {
		return reservation_no;
	}
	public void setReservation_no(int reservation_no) {
		this.reservation_no = reservation_no;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getClasses_no() {
		return classes_no;
	}
	public void setClasses_no(int classes_no) {
		this.classes_no = classes_no;
	}
	public String getUsers_id() {
		return users_id;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	public String getOrderlist_date() {
		return orderlist_date;
	}
	public void setOrderlist_date(String orderlist_date) {
		this.orderlist_date = orderlist_date;
	}
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	public int getOrderlist_state_no() {
		return orderlist_state_no;
	}
	public void setOrderlist_state_no(int orderlist_state_no) {
		this.orderlist_state_no = orderlist_state_no;
	}
	public int getOrderlist_no() {
		return orderlist_no;
	}
	public void setOrderlist_no(int orderlist_no) {
		this.orderlist_no = orderlist_no;
	}
	public int getStudio_no() {
		return studio_no;
	}
	public void setStudio_no(int studio_no) {
		this.studio_no = studio_no;
	}
	public String getChoice_date() {
		return choice_date;
	}
	public void setChoice_date(String choice_date) {
		this.choice_date = choice_date;
	}
	public int getClasses_info_no() {
		return classes_info_no;
	}
	public void setClasses_info_no(int classes_info_no) {
		this.classes_info_no = classes_info_no;
	}

	
	
}
