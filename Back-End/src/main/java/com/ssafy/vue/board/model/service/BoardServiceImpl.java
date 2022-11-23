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
//		int spl = SizeConstant.LIST_SIZE;
//		int start = (pgno - 1) * spl;
//		map.put("start", start + "");
//		map.put("spl", spl + "");
		
		logger.debug("서비스 listarticle 진입");
		
		Map<String, Object> param = new HashMap<String, Object>();
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		
		logger.debug("return 보드맵퍼.리스트아티클(맵)");
		return boardMapper.listArticle(param);
	}
	
	@Override
	public List<BoardDto> myListArticle(Map<String, String> map) throws Exception {
		int pgno = Integer.parseInt(map.get("pgno"));
//		int spl = SizeConstant.LIST_SIZE;
//		int start = (pgno - 1) * spl;
//		map.put("start", start + "");
//		map.put("spl", spl + "");
		
		logger.debug("서비스 listarticle 진입");
		
		Map<String, Object> param = new HashMap<String, Object>();
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		param.put("id", map.get("id"));
		logger.debug("return 보드맵퍼.리스트아티클(맵)");
		return boardMapper.myListArticle(param);
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if ("userid".equals(key))
			key = "user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int totalCount = boardMapper.getTotalArticleCount(param);
		System.out.println(totalCount);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);

		return pageNavigation;
	}

	@Override
	public List<BoardDto> preview(int num) throws Exception {
		return boardMapper.preview(num);
	}

	@Override
	public void writeArticle(BoardDto boardDto) throws Exception {
		boardMapper.writeArticle(boardDto);
		List<FileInfoDto> fileInfos = boardDto.getFileInfos();
		System.out.println("fileInfos : " + fileInfos);
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
	public void deleteArticle(int articleNo, String path) throws Exception {
		List<FileInfoDto> fileList = boardMapper.fileInfoList(articleNo);
		logger.info("files : {}", fileList);
		boardMapper.deleteFile(articleNo);
		replyService.deleteAll(articleNo);
		boardMapper.deleteArticle(articleNo);
		for(FileInfoDto fileInfoDto : fileList) {
			File file = new File(path + File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
			file.delete();
		}
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		boardMapper.modifyArticle(boardDto);

	}

}
