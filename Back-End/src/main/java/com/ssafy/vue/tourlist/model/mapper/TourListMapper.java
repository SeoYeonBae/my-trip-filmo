package com.ssafy.vue.tourlist.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.tourlist.model.TourListDto;

@Mapper
public interface TourListMapper {
	
	TourListDto recommend(int contentTypeId) throws SQLException;
	List<TourListDto> getData(Map<String, Integer> map) throws SQLException;
	
}
