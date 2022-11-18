package com.ssafy.vue.notice.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.vue.notice.model.NoticeDto;
import com.ssafy.vue.util.PageNavigation;

public interface NoticeService {

	int countArticle() throws Exception;
	int writeArticle(NoticeDto noticeDto) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	List<NoticeDto> listArticle(Map<String, String> map) throws Exception;
	List<NoticeDto> preview(int num) throws Exception;
	NoticeDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modifyArticle(Map<String, String> map) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
}
