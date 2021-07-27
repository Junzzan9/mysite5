package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[UserController.loginForm]");

		return "user/loginForm";
	}

	@RequestMapping(value = "/user/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {

		UserVo authUser = userService.getUser(userVo);

		if (authUser != null) {
			System.out.println("[로그인 성공]");
			session.setAttribute("authUser", authUser);

			return "redirect:/main";
		} else {
			System.out.println("[로그인 실패]");
			return "redirect:/user/loginForm?result=fail";
		}
	}
}
