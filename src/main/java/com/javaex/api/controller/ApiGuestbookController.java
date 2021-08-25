package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/api/guestbook")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<GuestbookVo> List() {
		System.out.println("[ApiGuestbookController.List]");

		List<GuestbookVo> guestbookList = guestbookService.getGuestList();
		System.out.println(guestbookList);

		return guestbookList;
	}
	
	
	//ajax add guestbook
	@ResponseBody
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.write]");
		
		System.out.println(guestbookVo);
		
		GuestbookVo resultVo = guestbookService.writeResultVo(guestbookVo);
		System.out.println("controller  -"+resultVo);
		
		return resultVo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestbookVo write2(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.write]  "+guestbookVo);
		
		System.out.println(guestbookVo);
		
		GuestbookVo resultVo = guestbookService.writeResultVo(guestbookVo);
		System.out.println("controller  -"+resultVo);
		
		return resultVo;
	}
	
	
	
	//ajax remove
	@ResponseBody
	@RequestMapping(value = "/remove", method = { RequestMethod.GET, RequestMethod.POST })
	public int remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.remove]");
		
		System.out.println(guestbookVo);
		
		int count = guestbookService.removeGuest(guestbookVo);
		
		return count;
	}
	
	@ResponseBody
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestbookVo read(@RequestBody GuestbookVo guestbookVo) {
		
		System.out.println(guestbookVo);
		
		guestbookVo = guestbookService.readGuestbookVo(guestbookVo);
		
		System.out.println(guestbookVo);
		return guestbookVo;
	}
	
	
	
}
