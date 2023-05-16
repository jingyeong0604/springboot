package com.shinhan.education.controller;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shinhan.education.repository.FreeBoardRepository;
import com.shinhan.education.repository.FreeRepiesRepository;

@Controller
public class FreeBoardController {
	
	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeRepiesRepository replyRepo;
	
	@GetMapping("/freeboard/eltest") //수정
	public void freeboard3(Model dataScope,HttpSession session) {
		dataScope.addAttribute("boardList",boardRepo.findAll());  
		dataScope.addAttribute("now",new Date());  
		dataScope.addAttribute("price",123456789);  
		dataScope.addAttribute("title", "This is a just sample");  
		dataScope.addAttribute("options", Arrays.asList("apple","banana","kiwi"));  
		session.setAttribute("userName", "진경");
	}
	
	@GetMapping("/freeboard/selectAll")
	public String freeboard2(Model dataScope) {
		dataScope.addAttribute("boardList",boardRepo.findAll());  
		return "freeboard/list";
	}
	
	@GetMapping("/freeboard/detail")//@RequestParam("bno") 생략되어있음
	public void freeboard1(Long bno, Model model) {
		boardRepo.findById(bno).ifPresent(board->{
			model.addAttribute("board",board);
		});
	}
	
	@GetMapping("/firstzone1")
	public void test1(Model model) {
		model.addAttribute("greeting","안녕");
		model.addAttribute("company","신한금융");
		//데이터 저장, 리턴 x
		
		
	}
	
	@GetMapping("/firstzone2")
	public String test2(Model model) {
		model.addAttribute("greeting","고고");
		model.addAttribute("company","국민은행");
		//데이터 저장, 리턴 x
		return "firstzone1"; //@GetMapping("/firstzone2")과 다른 타임리프 페이지 이름을
		//호출하고 싶을때!->요청의 이름 != 페이지 이름 일때 보여주고 싶은 html 페이지를 리턴해줌
		//templates/firstzone1.html로 forward된다
	}
	
	//aa폴더를 만들어서 firstzone3를 만들어주면 페이지 리턴을 안해도 됨.
	@GetMapping("/aa/firstzone3")
	public void test3(Model model) {
		model.addAttribute("greeting","고고~~~~");
		model.addAttribute("company","국민은행!");
		//데이터 저장, 리턴 x
	}
}
