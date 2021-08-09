package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/api/user")
public class ApiUserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/idcheck", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean idCheck(@ModelAttribute UserVo userVo) {
		System.out.println("컨-체크- Vo = " + userVo);

		userService.getUser(userVo);
		if (userVo != null) {
			return true;
		} else {
			return false;
		}
	}

}
