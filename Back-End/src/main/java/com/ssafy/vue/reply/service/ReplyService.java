package com.ssafy.vue.reply.service;

import com.ssafy.vue.reply.model.ReplyDto;

public interface ReplyService {
	void regist(ReplyDto replyDto) throws Exception;
	List<ReplyDto> list(int article_no) throws Exception;
	List<ReplyDto> list_by_id(String user_id) throws Exception;
	void modify(ReplyDto replyDto) throws Exception;
	void delete(int idx) throws Exception;
	void deleteAll(int article_no) throws Exception;
}
