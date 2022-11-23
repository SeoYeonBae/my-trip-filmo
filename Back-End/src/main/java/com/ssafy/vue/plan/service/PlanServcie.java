package com.ssafy.vue.plan.service;

import com.ssafy.vue.plan.model.PlanDto;

public interface PlanServcie {
	void regist(PlanDto planDto) throws Exception;
	void addPlanDetail(int idx) throws Exception;
}
