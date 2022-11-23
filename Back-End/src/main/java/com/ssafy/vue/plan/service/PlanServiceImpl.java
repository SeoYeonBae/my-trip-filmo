package com.ssafy.vue.plan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.plan.model.PlanDto;
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

}
