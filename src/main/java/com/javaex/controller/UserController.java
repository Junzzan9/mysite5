package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		System.out.println("[UserController.login]");
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
	
	@RequestMapping(value = "/user/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserController.joinForm]");
		
		
		return "user/joinForm";
	}
	
	@RequestMapping(value = "/user/joinOk", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinOk(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.joinOk]");
		System.out.println(userVo);
		userService.addUser(userVo);
		
		
		return "user/joinOk";
	}
	
	@RequestMapping(value = "/user/modifyForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(Model model ,HttpSession session) {
		System.out.println("controller : modifyForm");
		int no = ((UserVo)session.getAttribute("authUser")).getNo();
		
		
		UserVo userVo = userService.modifygetUser(no);
		
		model.addAttribute("userVo",userVo);
		
		return "user/modifyForm";
	}
	@RequestMapping(value = "/user/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify( @ModelAttribute UserVo userVo) {
		
		userService.modifyUser(userVo);
		
		return "redirect:/main";
	}
}
