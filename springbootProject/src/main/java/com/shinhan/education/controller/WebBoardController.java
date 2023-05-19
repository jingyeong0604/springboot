package com.shinhan.education.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.querydsl.core.types.Predicate;
import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo3.PageMarker;
import com.shinhan.education.vo3.PageVO;
import com.shinhan.education.vo3.WebBoard;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/webboard")//공통주소 추가
public class WebBoardController {
	@Autowired
	WebBoardRepository boardRepo;
	@Autowired
	WebReplyRepository replyRepo;
	
	
	@ApiOperation(value="개시판 등록화면", notes="게시판 등록화면!")
	//등록
	@GetMapping("/register.do")
	public void registerGet() {
		//System.out.println("123123");
		
	}
	
	@PostMapping("/register.do")
	public String registerPost(WebBoard board,RedirectAttributes attr) {
		WebBoard newBoard = boardRepo.save(board);
		if(newBoard!=null) {
			attr.addFlashAttribute("msg", "insert ok!!");
			//addFlashAttribute:새로고침하면 저장했던게 없어짐(1회성)
			//addAttribute:새로고침해도 유지
		}else {
			attr.addFlashAttribute("msg","insert fail!!");
		}
		return "redirect:list.do";
	}
	
	@PostMapping("/delete.do")
	public String deletePost(WebBoard board, PageVO pageVO, RedirectAttributes attr) {
		boardRepo.delete(board);
		//객체로 지움
		//Long bno로 매개변수를 바꿔서 bno로 삭제하기!
		attr.addFlashAttribute("msg", "delete ok!");
		//addFlashAttribute:새로고침하면 저장했던게 없어짐(1회성)
		//addAttribute:새로고침해도 유지
		attr.addAttribute("pageVO",pageVO.getPage());
		attr.addAttribute("size",pageVO.getSize());
		attr.addAttribute("keyword",pageVO.getKeyword());
		attr.addAttribute("type",pageVO.getType());
		return "redirect:list.do";
	}
	
	
	
	@PostMapping("/modify.do")
	public String updatePost(WebBoard board, PageVO pageVO, RedirectAttributes attr) {
		//System.out.println("board>>"+board);
		WebBoard savedBoard = boardRepo.save(board);//board수정
		
		if(savedBoard==null) {
			attr.addFlashAttribute("msg","Update failed!");
		}else {
			attr.addFlashAttribute("msg", "Update success!");
		}
		attr.addFlashAttribute("msg", "delete ok!");
		//addFlashAttribute:새로고침하면 저장했던게 없어짐(1회성)
		//addAttribute:새로고침해도 유지
		attr.addAttribute("pageVO",pageVO.getPage());
		attr.addAttribute("bno",board.getBno());
		attr.addAttribute("size",pageVO.getSize());
		attr.addAttribute("keyword",pageVO.getKeyword());
		attr.addAttribute("type",pageVO.getType());
		return "redirect:view.do";
	}
	
	
	
	@GetMapping("/modify.do")
	public void updateOrDelete(Long bno, Model model,PageVO pageVO) {
		boardRepo.findById(bno).ifPresent(board->{
			model.addAttribute("board", board);//board안에 board번호가 있음. view.html에서 사용함.
			model.addAttribute("pageVO", pageVO);
		});
	}
	
	
	@GetMapping("/view.do")
	public void selectById(Long bno, Model model,PageVO pageVO) {
		boardRepo.findById(bno).ifPresent(board->{
			model.addAttribute("board", board);//board안에 board번호가 있음. view.html에서 사용함.
			model.addAttribute("pageVO", pageVO);
		});
		
	}
	
	
	//전체조회!
	@GetMapping("/list.do")
	public void selectAll(PageVO pageVO, Model model, HttpServletRequest request) {
		if(pageVO==null) {
			pageVO=new PageVO();
			pageVO.setPage(1);
		}
	
		/*
		 * Page<WebBoard> p_result = pageMaker.getResult();
		 * System.out.println(p_result.getContent());
		 */
		/*
		 * Map<String, ?> flashMap=RequestContextUtils.getInputFlashMap(request);
		 * if(flashMap!=null) { Object message=flashMap.get("msg");
		 * System.out.println("(입력/삭제/수정에 대한 결과)message>>"+message);
		 * model.addAttribute(attributeValue) }
		 */
		Predicate pre=
				boardRepo.makePredicate(pageVO.getType(),pageVO.getKeyword());//predicate만듦->동적 sql만듦
	
		Pageable paging =pageVO.makePageable(pageVO.getPage(), "bno"); //몇페이지를 원하는지 paging 입력받기 0->asc /desc 어떤 컬럼으로 sort할건지 선택
		//Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		Page<WebBoard> result = boardRepo.findAll(pre, paging);
		/* List<WebBoard> blist = result.getContent(); 
		 * System.out.println("전체페이지수:"+ result.getTotalPages());
		 * System.out.println("전체 건수:"+ result.getTotalElements());*/
		PageMarker<WebBoard> pageMaker = new PageMarker<>(result, pageVO.getSize());
		
		//페이지를 만들어줌
		model.addAttribute("blist", pageMaker);
	}
}
