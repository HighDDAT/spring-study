package com.spring.mypage.user.service;

import com.spring.mypage.user.domain.LoginDTO;
import com.spring.mypage.user.domain.UserDTO;

public interface UserService {

	// 회원가입 처리
	void register(UserDTO userDTO) throws Exception;
	
	// 로그인 처리
	UserDTO login(LoginDTO loginDTO) throws Exception;
	
}
