package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
public class BoaedController {
	
	
	@Autowired
	private BoardService boardService;
	
	//board paging practice
	@RequestMapping(value = "/board/list2", method = { RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage, Model model,@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
		System.out.println("boardcontroller.list2");
		System.out.println(crtPage);
		
		Map<String,Object> listMap = boardService.getBoardList2(crtPage,keyword);
		System.out.println(listMap);
		
		model.addAttribute("listMap", listMap);
		
		return "board/list2";
	}
	
	
	@RequestMapping (value="/board/read", method={RequestMethod.GET,RequestMethod.POST})
	public String read(Model model,@RequestParam("no") int no) {
		System.out.println("boardcontroller.read");
		System.out.println(no);
		
		BoardVo boardVo = boardService.getBoard(no);
		model.addAttribute("boardVo",boardVo);
		
		return "board/read";
	}
	@RequestMapping(value = "/board/list", method = { RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, Model model) {
		System.out.println("boardcontroller.list");
		
		List<BoardVo> boardList = boardService.getBoardList(keyword);
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	@RequestMapping(value = "/board/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyform(@RequestParam int no, Model model) {
		System.out.println("boardcontroller.modifyform");

		BoardVo boardVo = boardService.getModifyBoard(no);
		
		model.addAttribute("boardVo", boardVo);
		
		return "board/modifyForm";
	}
	
	@RequestMapping(value = "/board/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("boardcontroller.modify");

		boardService.modify(boardVo);

		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/board/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("boardcontroller.writeform");

		return "board/writeForm";
	}
	
	@RequestMapping(value = "/board/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("boardcontroller.write");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		boardService.boardAdd(boardVo);

		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/board/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("boardcontroller.delete");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		boardService.boardDelete(boardVo);

		return "redirect:/board/list";
	}
	
}
