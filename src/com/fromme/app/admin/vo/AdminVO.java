package com.fromme.app.admin.vo;

import java.util.List;

import com.fromme.app.classes.vo.ClassesVO;

public class AdminVO {
	private IndexViewVO indexView;
	private int indexReserve;
	private int indexTotalReserve;
	private double tax;
	private List<ClassesVO> classList;
	
	public AdminVO() {;}

	public IndexViewVO getIndexView() {
		return indexView;
	}

	public void setIndexView(IndexViewVO indexView) {
		this.indexView = indexView;
	}

	public int getIndexReserve() {
		return indexReserve;
	}

	public void setIndexReserve(int indexReserve) {
		this.indexReserve = indexReserve;
	}

	public int getIndexTotalReserve() {
		return indexTotalReserve;
	}

	public void setIndexTotalReserve(int indexTotalReserve) {
		this.indexTotalReserve = indexTotalReserve;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public List<ClassesVO> getClassList() {
		return classList;
	}

	public void setClassList(List<ClassesVO> classList) {
		this.classList = classList;
	}
	
	
	
	
	
	
}


