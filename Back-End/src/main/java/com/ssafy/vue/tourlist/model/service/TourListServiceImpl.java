package com.ssafy.vue.tourlist.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.tourlist.model.TourListDto;
import com.ssafy.vue.tourlist.model.mapper.TourListMapper;

@Service
public class TourListServiceImpl implements TourListService {

	private TourListMapper tourlistMapper;
	
	@Autowired
	private TourListServiceImpl(TourListMapper tourListMapper) {
		this.tourlistMapper = tourListMapper;
	}
	
	@Override
	public TourListDto recommend(int contentTypeId) throws Exception {
		return tourlistMapper.recommend(contentTypeId);
	}

	@Override
	public List<TourListDto> getData(Map<String, Integer> map) throws Exception {
		return tourlistMapper.getData(map);
	}


}
