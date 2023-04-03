package com.spring.mypage.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private Date userJoinDate;
	private Date userLoginDate;
	private String userSignature;
	private String userImg;
	private int userPorint;
	
}
