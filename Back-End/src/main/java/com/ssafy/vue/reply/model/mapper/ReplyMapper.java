package com.ssafy.vue.reply.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.reply.model.ReplyDto;

@Mapper
public interface ReplyMapper {
	void regist(ReplyDto replyDto) throws SQLException;
	List<ReplyDto> list(int article_no) throws SQLException;
	List<ReplyDto> listById(String user_id) throws SQLException;
	void modify(ReplyDto replyDto) throws SQLException;
	void delete(int idx) throws SQLException;
	void deleteAll(int article_no) throws SQLException;
}
