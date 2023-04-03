package com.spring.mypage.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDTO {
	
	private String userId;
	private String userPw;
	private boolean userCookie;

}
