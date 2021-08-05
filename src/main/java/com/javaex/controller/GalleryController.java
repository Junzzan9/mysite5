package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value="/list", method={RequestMethod.GET,RequestMethod.POST})
	public String list() {
		
		
		
		return "gallery/list";
	}
	
	@RequestMapping(value="/write", method={RequestMethod.GET,RequestMethod.POST})
	public String writeGallery(@RequestParam("file") MultipartFile file,HttpSession session,@ModelAttribute GalleryVo galleryVo) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		galleryVo.setUserNo(authUser.getNo());
		
		galleryService.writeGallery(galleryVo, file);
		
		return "redirect:/gallery/list";
	}

}
