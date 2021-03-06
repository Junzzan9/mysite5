package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	// 방명록 리스트 가져오기
	public List<GuestbookVo> getGuestList() {
		System.out.println("[guestbookService.getGuestList()]");

		return guestbookDao.selectList();
	}

	// 방명록 글 저장
	public int writeGuest(GuestbookVo guestbookVo) {
		System.out.println("[guestbookService.writeGuest()]");

		return guestbookDao.insertGuestbook(guestbookVo);
	}

	// 방명록 글 삭제
	public int removeGuest(GuestbookVo guestbookVo) {
		System.out.println("guestbookService.removeGuest");

		return guestbookDao.deleteGuest(guestbookVo);
	}

	// ajax guestbook
	public GuestbookVo writeResultVo(GuestbookVo guestbookVo) {
		System.out.println("[guestbookService.writeResultVo()]");

		// save guestbook
		System.out.println(guestbookVo);
		int count = guestbookDao.insertGuestbookKey(guestbookVo);
		System.out.println(guestbookVo);

		// load guestbook(recent pk)
		int no = guestbookVo.getNo();

		return guestbookDao.selectGuestbook(no);
	}

	public GuestbookVo readGuestbookVo(GuestbookVo guestbookVo) {
		int no = guestbookVo.getNo();

		return guestbookDao.selectGuestbook(no);
	}

}