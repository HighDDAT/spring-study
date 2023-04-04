package com.spring.mypage.user.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.mypage.user.domain.LoginDTO;
import com.spring.mypage.user.domain.UserDTO;
import com.spring.mypage.user.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService{

	private final UserDAO userDAO;

	@Inject
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
