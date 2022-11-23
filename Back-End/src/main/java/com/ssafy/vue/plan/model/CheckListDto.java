package com.ssafy.vue.plan.model;

public class CheckListDto {

	int plan_idx;
	String todo;
	
	public CheckListDto() {
		super();
	}
	public int getPlan_idx() {
		return plan_idx;
	}
	public void setPlan_idx(int plan_idx) {
		this.plan_idx = plan_idx;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
}	
