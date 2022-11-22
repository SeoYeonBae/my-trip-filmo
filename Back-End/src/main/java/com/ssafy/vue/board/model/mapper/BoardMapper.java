package com.ssafy.vue.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.board.model.BoardDto;

@Mapper
public interface BoardMapper {
	
	int countArticle() throws SQLException;
	int writeArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> listArticle(Map<String, Object> map) throws SQLException;
//	List<BoardDto> listById(Map<String, Object> map) throws SQLException;  유저 아이디로 내가 쓴글 받아오기
	List<BoardDto> preview(int num) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
}
