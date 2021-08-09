package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	// field
	@Autowired
	private SqlSession sqlSession;

	// constructor

	// method g/s

	// method general
	public UserVo selectUser(UserVo userVo) {

		System.out.println("[UserDao.selectUser]        "+userVo);

		if (userVo.getpassward()==null||userVo.getpassward().equals("")) {
			userVo = sqlSession.selectOne("user.selectidcheck", userVo);
			System.out.println(userVo);
		} else {
			userVo = sqlSession.selectOne("user.selectUser", userVo);
			System.out.println(userVo);
		}
		return userVo;

	}
	
	public UserVo selectUser(String id) {
		return sqlSession.selectOne("user.selectUserById", id);
	}

	public int insertUser(UserVo userVo) {

		System.out.println("[UserDao.insertUser");

		System.out.println(userVo);

		return sqlSession.insert("user.insertUser", userVo);
	}

	public UserVo selectModifyUser(int no) {
		System.out.println("[UserDao.selectModifyUser]");

		return sqlSession.selectOne("user.selectModifyUser", no);
	}

	public int updateUser(UserVo userVo) {

		return sqlSession.update("user.updateUser", userVo);
	}
}
