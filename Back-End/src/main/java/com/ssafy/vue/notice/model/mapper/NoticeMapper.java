package com.ssafy.vue.notice.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.notice.model.NoticeDto;

@Mapper
public interface NoticeMapper {

	int countArticle() throws SQLException;
	int writeArticle(NoticeDto noticeDto) throws SQLException;
	List<NoticeDto> listArticle(Map<String, Object> map) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	List<NoticeDto> preview(int num) throws SQLException;
	NoticeDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modifyArticle(NoticeDto noticeDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;
}
