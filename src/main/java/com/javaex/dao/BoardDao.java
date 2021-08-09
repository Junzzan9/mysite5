package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	//get total post 
	public int selectTotalCnt(String keyword) {
		System.out.println("Dao.selectTotalCnt");
		
		
		return sqlSession.selectOne("board.selectTotalCnt",keyword);
	}
	
	
	public List<BoardVo> selectList2(int startRnum,int endRnum,String keyword){
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("startRnum", startRnum);
		pMap.put("endRnum", endRnum);
		pMap.put("keyword", keyword);
		
		System.out.println(pMap);
		
		return sqlSession.selectList("board.selectList2",pMap);
	}
	
	
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
