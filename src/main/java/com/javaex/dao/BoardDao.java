package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public int updateHit(int no) {
		System.out.println("BoardDao.updateHit");

		int count = sqlSession.update("board.updateHit", no);

		return count;
	}

	public BoardVo selectBoard(int no) {
		System.out.println("BoardDao.selectOne");

		return sqlSession.selectOne("board.selectBoard", no);
	}

	public List<BoardVo> selectList(String keyword) {
		System.out.println("BoardDao.selectList");

		return sqlSession.selectList("board.selectList", keyword);
	}

	public int updateBoard(BoardVo boardVo) {
		System.out.println("BoardDao.updateboard");

		return sqlSession.update("board.updateBoard", boardVo);
	}

	public int insertBoard(BoardVo boardVo) {
		System.out.println("boarddao.insertboard");

		return sqlSession.insert("board.insertBoard", boardVo);
	}
	
	public int deleteBoard(BoardVo boardVo) {
		System.out.println("boarddao.deleteboard");

		return sqlSession.delete("board.deleteBoard", boardVo);
	}
	
}
