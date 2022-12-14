package com.ssafy.vue.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.vue.board.model.BoardDto;
import com.ssafy.vue.util.PageNavigation;

public interface BoardService {

	int countArticle() throws Exception;
	List<BoardDto> listArticle(Map<String, String> map) throws Exception;
	void writeArticle(BoardDto boardDto) throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modifyArticle(BoardDto boardDto) throws Exception;
	void deleteArticle(int articleNo, String path) throws Exception;
	List<BoardDto> myListArticle(Map<String, String> map) throws Exception;
}
