package com.ssafy.vue.reply.service;

import java.util.List;
import com.ssafy.vue.reply.model.ReplyDto;

public interface ReplyService {
	void regist(ReplyDto replyDto) throws Exception;
	List<ReplyDto> list(int article_no) throws Exception;
	List<ReplyDto> myList(String user_id) throws Exception;
	void modify(ReplyDto replyDto) throws Exception;
	void delete(int idx) throws Exception;
	void deleteAll(int article_no) throws Exception;
}
