package com.ssafy.vue.do17.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.do17.model.DoDto;

@Mapper
public interface DoMapper {

	List<DoDto> list() throws SQLException;
}
