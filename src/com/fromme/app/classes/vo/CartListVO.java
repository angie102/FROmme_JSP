package com.fromme.app.classes.vo;

public class CartListVO {
	private String users_id;
	private int classes_no;
	private String classes_name;
	private String choice_date;
	private int cart_no;
	private int classes_info_no;
	private int cart_quantity;
	private int classes_price;
	private int cart_sum_price;
	
	
	public int getClasses_no() {
		return classes_no;
	}
	public void setClasses_no(int classes_no) {
		this.classes_no = classes_no;
	}
	public int getCart_no() {
		return cart_no;
	}
	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}
	public String getUsers_id() {
		return users_id;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	public String getClasses_name() {
		return classes_name;
	}
	public void setClasses_name(String classes_name) {
		this.classes_name = classes_name;
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
	public int getCart_quantity() {
		return cart_quantity;
	}
	public void setCart_quantity(int cart_quantity) {
		this.cart_quantity = cart_quantity;
	}
	public int getClasses_price() {
		return classes_price;
	}
	public void setClasses_price(int classes_price) {
		this.classes_price = classes_price;
	}
	public int getCart_sum_price() {
		return cart_sum_price;
	}
	public void setCart_sum_price(int cart_sum_price) {
		this.cart_sum_price = cart_sum_price;
	}


}
