package com.ssafy.vue.do17.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.do17.model.DoDto;
import com.ssafy.vue.do17.model.mapper.DoMapper;

@Service
public class DoServiceImpl implements DoService {

	private DoMapper doMapper;
	
	@Autowired
	private DoServiceImpl(DoMapper doMapper) {
		this.doMapper = doMapper;
	}
		
	@Override
	public List<DoDto> list() throws Exception {
		return doMapper.list();
	}

}
