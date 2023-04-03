package com.spring.mypage.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.mypage.domain.LoginDTO;
import com.spring.mypage.domain.UserDTO;

@Repository
public class UserDAOImpl implements UserDAO{
	
	private static String NAMESPACE = "com.spring.mypage.mappers.user.UserMapper";
	
	private final SqlSession sqlSession;

	@Inject
	public UserDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 회원가입 처리
	@Override
	public void register(UserDTO userDTO) throws Exception {
		sqlSession.insert(NAMESPACE + ".register", userDTO);
	}

	// 로그인 처리
	@Override
	public UserDTO login(LoginDTO loginDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".login", loginDTO);
	}
	
	

}
