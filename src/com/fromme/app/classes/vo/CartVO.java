package com.fromme.app.classes.vo;

public class CartVO{	
	private int cart_no;
	private String cart_date;
	private int cart_quantity;
	private String choice_date;
	private int classes_info_no;
	private int classes_no;
	private String users_id;
	
	public CartVO() {;}

	
	public int getClasses_info_no() {
		return classes_info_no;
	}
	public void setClasses_info_no(int classes_info_no) {
		this.classes_info_no = classes_info_no;
	}

	public int getCart_no() {
		return cart_no;
	}

	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}

	public int getCart_quantity() {
		return cart_quantity;
	}

	public void setCart_quantity(int cart_quantity) {
		this.cart_quantity = cart_quantity;
	}

	public String getChoice_date() {
		return choice_date;
	}

	public void setChoice_date(String choice_date) {
		this.choice_date = choice_date;
	}

	public String getCart_date() {
		return cart_date;
	}

	public void setCart_date(String cart_date) {
		this.cart_date = cart_date;
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
	

}
