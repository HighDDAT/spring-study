package com.spring.mypage.service;

import com.spring.mypage.domain.LoginDTO;
import com.spring.mypage.domain.UserDTO;

public interface UserService {

	// 회원가입 처리
	void register(UserDTO userDTO) throws Exception;
	
	// 로그인 처리
	UserDTO login(LoginDTO loginDTO) throws Exception;
	
}
