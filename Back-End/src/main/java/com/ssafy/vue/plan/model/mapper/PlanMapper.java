package com.ssafy.vue.plan.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.plan.model.PlanDto;


@Mapper
public interface PlanMapper {
	boolean regist(PlanDto planDto) throws SQLException;
	void addPlanDetail(PlanDto planDto) throws SQLException;
	
}
