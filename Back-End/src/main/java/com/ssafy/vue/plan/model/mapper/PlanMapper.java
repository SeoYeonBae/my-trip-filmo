package com.ssafy.vue.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.plan.model.PlanDto;
import com.ssafy.vue.plan.model.PlanIdxDto;
import com.ssafy.vue.tourlist.model.TourListDto;


@Mapper
public interface PlanMapper {
	boolean regist(PlanDto planDto) throws SQLException;
	void addPlanDetail(PlanIdxDto planIdxDto) throws SQLException;
	int getLastIdx(String user_id) throws SQLException;
	void deletePlan(int plan_idx) throws SQLException;
	List<Integer> getMyPlan(int plan_idx) throws SQLException;
	TourListDto getTourInfo(Integer idx) throws SQLException;
	void registInfo(PlanDto planDto) throws SQLException;
}
