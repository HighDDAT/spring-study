package com.spring.mypage.service;

import org.springframework.stereotype.Service;

import com.spring.mypage.domain.LoginDTO;
import com.spring.mypage.domain.UserDTO;
import com.spring.mypage.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService{

	private final UserDAO userDAO;

	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	// 회원가입 처리
	@Override
	public void register(UserDTO userDTO) throws Exception {
		userDAO.register(userDTO);
		
	}

	// 로그인 처리
	@Override
	public UserDTO login(LoginDTO loginDTO) throws Exception {
		return userDAO.login(loginDTO);
	}
	
	
	
}
