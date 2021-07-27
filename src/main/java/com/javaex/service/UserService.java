package com.javaex.service;

import org.springframework.stereotype.Repository;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Repository
public class UserService {
		
	private UserDao userDao;
	
	//로그인 사용자정보 가져오기
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserService.getUser]");
		
		UserVo authUser=userDao.selectUser(userVo);
		
		
		return authUser;
	}
	
}
