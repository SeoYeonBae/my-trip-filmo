package com.ssafy.vue.reply.model;

public class ReplyDto {
	private int idx;
	private String user_id;
	private String content;
	private String regist_time;
	private int article_no;
	
	public ReplyDto() {
		super();
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegist_time() {
		return regist_time;
	}

	public void setRegist_time(String regist_time) {
		this.regist_time = regist_time;
	}

	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	@Override
	public String toString() {
		return "ReplyDto [idx=" + idx + ", user_id=" + user_id + ", content=" + content + ", regist_time=" + regist_time
				+ ", article_no=" + article_no + "]";
	}
	
}
