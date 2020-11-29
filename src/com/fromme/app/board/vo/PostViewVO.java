package com.fromme.app.board.vo;

public class PostViewVO extends PostVO {
	private int reply_count;
	private int users_authority;
	
	public PostViewVO() {;}
	
	public int getReply_count() {
		return reply_count;
	}

	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}

	public int getUsers_authority() {
		return users_authority;
	}

	public void setUsers_authority(int users_authority) {
		this.users_authority = users_authority;
	}
}
