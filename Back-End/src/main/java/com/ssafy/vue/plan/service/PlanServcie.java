package com.ssafy.vue.plan.service;

import com.ssafy.vue.plan.model.PlanDto;
import com.ssafy.vue.plan.model.PlanIdxDto;

public interface PlanServcie {
	void regist(PlanDto planDto) throws Exception;
	void addPlanDetail(PlanIdxDto planIdxDto) throws Exception;
	int getLastIdx(String user_id) throws Exception;
	void deletePlan(int plan_idx)  throws Exception;
}
