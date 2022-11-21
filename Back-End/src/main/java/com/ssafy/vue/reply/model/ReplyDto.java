package com.ssafy.vue.reply.model;

public class ReplyDto {
	private String user_id;
	private String content;
	private String regist_time;
	private int article_no;
	
	public ReplyDto() {
		super();
	}
	public int getBoard_id() {
		return article_no;
	}
	public void setBoard_id(int article_no) {
		this.article_no = article_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getReply_content() {
		return content;
	}
	public void setReply_content(String content) {
		this.content = content;
	}
	public String getRegist_time() {
		return regist_time;
	}
	public void setRegist_time(String regist_time) {
		this.regist_time = regist_time;
	}
	@Override
	public String toString() {
		return "ReplyDto [user_id=" + user_id + ", content=" + content + ", regist_time=" + regist_time
				+ ", article_no=" + article_no + "]";
	}
	
}
