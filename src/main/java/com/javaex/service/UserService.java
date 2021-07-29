package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Repository
public class UserService {

	@Autowired
	private UserDao userDao;

	// 로그인 사용자정보 가져오기
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserService.getUser]");

		UserVo authUser = userDao.selectUser(userVo);

		return authUser;
	}

	// 회원가입 유저 추가

	public int addUser(UserVo userVo) {
		System.out.println("userservice.adduser");
		System.out.println(userVo);
		return userDao.insertUser(userVo);

	}

	public UserVo modifygetUser(int no) {
		System.out.println("userService modifyForm()");

		return userDao.selectModifyUser(no);
	}

	public int modifyUser(UserVo userVo) {
		System.out.println("userservice.modifyuser");
		System.out.println(userVo);

		return userDao.updateUser(userVo);
	}
}
