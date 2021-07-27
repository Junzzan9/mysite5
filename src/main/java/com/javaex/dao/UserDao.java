package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;


@Repository
public class UserDao {
	
	//field
	@Autowired
	private SqlSession sqlSession;
	
	//constructor
	
	//method g/s
	
	//method general
	public UserVo selectUser(UserVo userVo) {
		
		
		System.out.println("[UserDao.selectUser]");
		
		System.out.println(userVo);
		
		UserVo authUser = sqlSession.selectOne("user.selectUser",userVo);
		
		System.out.println(authUser);
		
		return authUser;
	}
}
