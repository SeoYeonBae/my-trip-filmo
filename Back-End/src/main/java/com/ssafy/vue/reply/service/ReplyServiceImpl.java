package com.ssafy.vue.reply.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.board.model.mapper.BoardMapper;
import com.ssafy.vue.board.model.service.BoardServiceImpl;
import com.ssafy.vue.reply.model.ReplyDto;
import com.ssafy.vue.reply.model.mapper.ReplyMapper;


@Service
public class ReplyServiceImpl implements ReplyService {
	private final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	private ReplyMapper replyMapper;
	
	@Autowired
	private ReplyServiceImpl(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}
		
	
	@Override
	public void regist(ReplyDto replyDto) throws Exception {
		// TODO Auto-generated method stub
		replyMapper.regist(replyDto);

	}

	@Override
	public List<ReplyDto> list(int article_no) throws Exception {
		// TODO Auto-generated method stub
		return replyMapper.list(article_no);
	}

	@Override
	public List<ReplyDto> listById(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return replyMapper.listById(user_id);
	}

	@Override
	public void modify(ReplyDto replyDto) throws Exception {
		// TODO Auto-generated method stub
		replyMapper.modify(replyDto);

	}

	@Override
	public void delete(int idx) throws Exception {
		// TODO Auto-generated method stub
		replyMapper.delete(idx);

	}

	@Override
	public void deleteAll(int article_no) throws Exception {
		// TODO Auto-generated method stub
		replyMapper.deleteAll(article_no);
	}

}
