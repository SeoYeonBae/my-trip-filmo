package com.ssafy.vue.sigungu.model.service;

import java.util.List;

import com.ssafy.vue.sigungu.model.SigunguDto;

public interface SigunguService {
	
	List<SigunguDto> list(int areaCode) throws Exception;
}
