package com.ssafy.vue.notice.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.vue.notice.model.NoticeDto;
import com.ssafy.vue.notice.model.mapper.NoticeMapper;
import com.ssafy.vue.util.PageNavigation;
import com.ssafy.vue.util.SizeConstant;

@Service
public class NoticeSerciveImpl implements NoticeService {

	private NoticeMapper noticeMapper;
	
	private NoticeSerciveImpl(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}
	
	@Override
	public int countArticle() throws Exception {
		return noticeMapper.countArticle();
	}

	@Override
	public List<NoticeDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);

		return noticeMapper.listArticle(param);
	}

	@Override
	public int writeArticle(NoticeDto noticeDto) throws Exception {
		return noticeMapper.writeArticle(noticeDto);
	}
	
	@Override
	public NoticeDto getArticle(int articleNo) throws Exception {
		return noticeMapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		noticeMapper.updateHit(articleNo);
	}

	@Override
	public void modifyArticle(NoticeDto noticeDto) throws Exception {
		noticeMapper.modifyArticle(noticeDto);	
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		noticeMapper.deleteArticle(articleNo);
	}



}
