package com.ssafy.vue.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.board.model.BoardDto;
import com.ssafy.vue.board.model.FileInfoDto;

@Mapper
public interface BoardMapper {
	
	int countArticle() throws SQLException;
	int writeArticle(BoardDto boardDto) throws SQLException;
	void registerFile(BoardDto boardDto) throws Exception;
	List<BoardDto> listArticle(Map<String, Object> map) throws SQLException;
	List<BoardDto> myListArticle(Map<String, Object> map) throws SQLException;
	List<BoardDto> preview(int num) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	List<FileInfoDto> fileInfoList(int articleNo) throws Exception;
	void deleteFile(int articleNo) throws Exception;
	
}
