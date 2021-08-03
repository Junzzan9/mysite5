package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	// 방명록 리스트 가져오기
	public List<GuestbookVo> selectList() {
		System.out.println("[guestbookDao.selectList()]");

		return sqlSession.selectList("guestbook.selectList");
	}

	// 방명록 글 저장
	public int insertGuestbook(GuestbookVo guestbookVo) {
		System.out.println("[guestbookDao.insertGuestbook()]");

		return sqlSession.insert("guestbook.insertGuestbook", guestbookVo);
	}

	// 방명록 글 삭제
	public int deleteGuest(GuestbookVo guestbookVo) {
		System.out.println("[guestbookDao.deleteBoard()]");

		return sqlSession.delete("guestbook.deleteGuest", guestbookVo);
	}
	
	// selectkey
	
	public int insertGuestbookKey(GuestbookVo guestbookVo) {
		
		System.out.println("[guestbookDao.insertGuestbookKey()]");
		
		return sqlSession.insert("guestbook.insertGuestbookKey", guestbookVo);
		
	}
	
	//selectone
	
	public GuestbookVo selectGuestbook(int no) {
		System.out.println("[guestbookDao.selectGuestbook()]");
		
		return sqlSession.selectOne("guestbook.selectGuestbook",no);
	}

}