package com.ssafy.vue.board.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.board.model.BoardDto;
import com.ssafy.vue.board.model.FileInfoDto;
import com.ssafy.vue.board.model.mapper.BoardMapper;
import com.ssafy.vue.reply.service.ReplyService;
import com.ssafy.vue.util.PageNavigation;
import com.ssafy.vue.util.SizeConstant;

@Service
public class BoardServiceImpl implements BoardService {

	private final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	private BoardMapper boardMapper;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
		
	@Override
	public int countArticle() throws Exception {
		return boardMapper.countArticle();
	}

	@Override
	public List<BoardDto> listArticle(Map<String, String> map) throws Exception {
		int pgno = Integer.parseInt(map.get("pgno"));
		
		Map<String, Object> param = new HashMap<String, Object>();
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		
		return boardMapper.listArticle(param);
	}
	
	@Override
	public void writeArticle(BoardDto boardDto) throws Exception {
		boardMapper.writeArticle(boardDto);
		List<FileInfoDto> fileInfos = boardDto.getFileInfos();
		if (fileInfos != null && !fileInfos.isEmpty()) {
			boardMapper.registerFile(boardDto);
		}
	}
	
	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		return boardMapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		boardMapper.updateHit(articleNo);

	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		boardMapper.modifyArticle(boardDto);
	}

	@Override
	public void deleteArticle(int articleNo, String path) throws Exception {
		List<FileInfoDto> fileList = boardMapper.fileInfoList(articleNo);
		boardMapper.deleteFile(articleNo);
		replyService.deleteAll(articleNo);
		boardMapper.deleteArticle(articleNo);
		for(FileInfoDto fileInfoDto : fileList) {
			File file = new File(path + File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
			file.delete();
			File sfile = new File(path + File.separator + fileInfoDto.getSaveFolder() + File.separator + "s_" + fileInfoDto.getSaveFile());
			sfile.delete();
		}
	}

	@Override
	public List<BoardDto> myListArticle(Map<String, String> map) throws Exception {
		int pgno = Integer.parseInt(map.get("pgno"));
		
		Map<String, Object> param = new HashMap<String, Object>();
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		param.put("id", map.get("id"));
		
		return boardMapper.myListArticle(param);
	}

}
