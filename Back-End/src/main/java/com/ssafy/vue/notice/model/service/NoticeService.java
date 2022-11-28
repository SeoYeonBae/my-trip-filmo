package com.ssafy.vue.notice.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.vue.notice.model.NoticeDto;
import com.ssafy.vue.util.PageNavigation;

public interface NoticeService {

	List<NoticeDto> listArticle(Map<String, String> map) throws Exception;
	int writeArticle(NoticeDto noticeDto) throws Exception;
	NoticeDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modifyArticle(NoticeDto noticeDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	int countArticle() throws Exception;
}
