package com.ssafy.vue.plan.service;

import java.util.List;

import com.ssafy.vue.plan.model.PlanDto;
import com.ssafy.vue.plan.model.PlanIdxDto;
import com.ssafy.vue.tourlist.model.TourListDto;

public interface PlanServcie {
	void regist(PlanDto planDto) throws Exception;
	void addPlanDetail(PlanIdxDto planIdxDto) throws Exception;
	int getLastIdx(String user_id) throws Exception;
	void deletePlan(int plan_idx) throws Exception;
	List<Integer> getMyPlan(int plan_idx) throws Exception;
	TourListDto getTourInfo(Integer idx) throws Exception;
}
