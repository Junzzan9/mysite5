package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping(value = "/api/guestbook/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<GuestbookVo> List() {
		System.out.println("[ApiGuestbookController.List]");

		List<GuestbookVo> guestbookList = guestbookService.getGuestList();
		System.out.println(guestbookList);

		return guestbookList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/guestbook/write", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.write]");
		
		System.out.println(guestbookVo);
		
		GuestbookVo resultVo = guestbookService.writeResultVo(guestbookVo);
		System.out.println("controller"+resultVo);
		return resultVo;
	}
	
	
}
