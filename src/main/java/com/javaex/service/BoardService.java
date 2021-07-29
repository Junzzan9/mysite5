package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

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
		
		return boardDao.insertBoard(boardVo);
	}
	
	public int boardDelete(BoardVo boardVo) {
		System.out.println("boardservice.boarddelete");

		return boardDao.deleteBoard(boardVo);
	}
}
