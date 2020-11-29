package com.fromme.app.admin.vo;

import com.fromme.app.user.vo.UserVO;

public class UserListVO extends UserVO{
	private int users_ReservationCnt;
	private int users_Amount;
	private int users_PostCnt;
	private int users_ReplyCnt;
	
	public UserListVO() {;}
	
	
	public int getUsers_ReservationCnt() {
		return users_ReservationCnt;
	}



	public void setUsers_ReservationCnt(int users_ReservationCnt) {
		this.users_ReservationCnt = users_ReservationCnt;
	}



	public int getUsers_Amount() {
		return users_Amount;
	}



	public void setUsers_Amount(int users_Amount) {
		this.users_Amount = users_Amount;
	}



	public int getUsers_PostCnt() {
		return users_PostCnt;
	}



	public void setUsers_PostCnt(int users_PostCnt) {
		this.users_PostCnt = users_PostCnt;
	}



	public int getUsers_ReplyCnt() {
		return users_ReplyCnt;
	}



	public void setUsers_ReplyCnt(int users_ReplyCnt) {
		this.users_ReplyCnt = users_ReplyCnt;
	}



		
}
