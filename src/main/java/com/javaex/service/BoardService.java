package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	// list2
	public Map<String,Object> getBoardList2(int crtPage,String keyword) {
		System.out.println("BoardService.getBoardList2");
		System.out.println(crtPage);

		// crtPage
		/*
		 * if(crtPage<=0) { crtPage=1; }
		 */

		crtPage = (crtPage > 0) ? crtPage : 1;

		// getList

		int listCnt = 10;

		// Get Total Count (paging)
		int totalCount = boardDao.selectTotalCnt(keyword);
		System.out.println(totalCount);
		// start no
		int startRnum = (crtPage - 1) * listCnt + 1;
		System.out.println(startRnum);
		// end no
		int endRnum = crtPage * listCnt;
		System.out.println(endRnum);
		List<BoardVo> boardList = boardDao.selectList2(startRnum, endRnum,keyword);


		// button per page
		int pageBtnCount = 5;

		// lastbutton
		int endPageBtnNo = (int) (Math.ceil(crtPage / (double) pageBtnCount)) * pageBtnCount;

		// startpagebutton
		int startPageBtnNo = endPageBtnNo - pageBtnCount + 1;

		// next 화살표 유무
		boolean next = false;
		if ((endPageBtnNo * listCnt) < totalCount) {
			next = true;
		}else {
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
		}

		// prev
		boolean prev = false;
		if (startPageBtnNo != 1) {
			prev = true;
		}
		
		//return for map
		Map<String,Object> listMap = new HashMap<String,Object>();
		listMap.put("boardList", boardList);
		listMap.put("prev",prev);
		listMap.put("next",next);
		listMap.put("startPageBtnNo",startPageBtnNo);
		listMap.put("endPageBtnNo",endPageBtnNo);
		listMap.put("keyword", keyword);
		
		

		return listMap;
	}

	public BoardVo getBoard(int no) {
		System.out.println("BoardService.getBoard");

		// Hit
		boardDao.updateHit(no);

		// read
		BoardVo boardVo = boardDao.selectBoard(no);

		return boardVo;
	}

	public List<BoardVo> getBoardList(String keyword) {
		System.out.println("BoardService.getBoardList");

		List<BoardVo> boardList = boardDao.selectList(keyword);

		return boardList;
	}

	public BoardVo getModifyBoard(int no) {
		System.out.println("BoardService.getModifyBoard");

		BoardVo boardVo = boardDao.selectBoard(no);

		return boardVo;
	}

	public int modify(BoardVo boardVo) {
		System.out.println("BoardService.modifyBoard");

		return boardDao.updateBoard(boardVo);
	}

	public int boardAdd(BoardVo boardVo) {
		System.out.println("BoardService.boardadd");

		for (int i = 0; i < 127; i++) {
			boardVo.setTitle(i+" + " +i+ "은 귀요미");
			boardVo.setContent(i + " + " + i + " 는 귀요미");
			boardDao.insertBoard(boardVo);
		}

		return 1;
		// return boardDao.insertBoard(boardVo);
	}

	public int boardDelete(BoardVo boardVo) {
		System.out.println("boardservice.boarddelete");

		return boardDao.deleteBoard(boardVo);
	}
}
