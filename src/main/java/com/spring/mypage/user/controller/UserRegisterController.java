package com.spring.mypage.user.controller;

import javax.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mypage.domain.UserDTO;
import com.spring.mypage.service.UserService;

@Controller
@RequestMapping("/user")
public class UserRegisterController {
	
	private final UserService userService;
	
	@Inject
	public UserRegisterController(UserService userService) {
		this.userService = userService;
	}
	
	// 회원가입 페이지
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGET() throws Exception {
		return "/user/register";
	}
	
	// 회원가입 처리
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(UserDTO userDTO, RedirectAttributes redirectAttributes) throws Exception {
		
		// 비밀번호 암호화 처리
		String hashedPw = BCrypt.hashpw(userDTO.getUserPw(), BCrypt.gensalt());
		userDTO.setUserPw(hashedPw);
		userService.register(userDTO);
		redirectAttributes.addFlashAttribute("msg", "REGISTERED");
		
		return "redirect:/user/login";
	}
	
}
