package com.ssafy.vue.plan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.plan.model.PlanDto;
import com.ssafy.vue.plan.model.PlanIdxDto;
import com.ssafy.vue.plan.model.mapper.PlanMapper;

@Service
public class PlanServiceImpl implements PlanServcie  {
	private final Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);
	
	private PlanMapper planMapper;
	
	@Autowired
	private PlanServiceImpl(PlanMapper planMapper) {
		this.planMapper = planMapper;
	}
		
	@Override
	public void regist(PlanDto planDto) throws Exception {
		planMapper.regist(planDto);
	}

	@Override
	public void addPlanDetail(PlanIdxDto planIdxDto) throws Exception {
		planMapper.addPlanDetail(planIdxDto);
	}

	@Override
	public int getLastIdx(String user_id) throws Exception {
		return planMapper.getLastIdx(user_id);
	}

	@Override
	public void deletePlan(int plan_idx) throws Exception {
		planMapper.deletePlan(plan_idx);
	}

}
