package com.spring.mypage.user.persistence;

import com.spring.mypage.user.domain.LoginDTO;
import com.spring.mypage.user.domain.UserDTO;

public interface UserDAO {
	
	// 회원가입
	void register (UserDTO userDTO) throws Exception;
	
	// 로그인
	UserDTO login(LoginDTO loginDTO) throws Exception;

}
