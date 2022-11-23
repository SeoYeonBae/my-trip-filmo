package com.ssafy.vue.plan.model;


public class PlanDto {

	int plan_idx;
	String title;
	String start_date;
	String end_date;
	String user_id;
	String invited_user;
	
	public PlanDto() {
		super();
	}
	public int getPlan_idx() {
		return plan_idx;
	}
	public void setPlan_idx(int plan_idx) {
		this.plan_idx = plan_idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getInvited_user() {
		return invited_user;
	}
	public void setInvited_user(String invited_user) {
		this.invited_user = invited_user;
	}
	@Override
	public String toString() {
		return "PlanDto [plan_idx=" + plan_idx + ", title=" + title + ", start_date=" + start_date + ", end_date="
				+ end_date + ", user_id=" + user_id + ", invited_user=" + invited_user + "]";
	}
	
}
