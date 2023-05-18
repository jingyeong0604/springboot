package com.shinhan.education.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo3.WebBoard;
import com.shinhan.education.vo3.WebReply;

@RestController
@RequestMapping("/replies")
public class WebReplyController {
	@Autowired
	WebReplyRepository replyRepo;
	@Autowired
	WebBoardRepository boardRepo;
	
	private ResponseEntity<List<WebReply>> makeReturn(Long bno, HttpStatus status){
		WebBoard board = new WebBoard();
		board.setBno(bno);//board를 가져올 필욘없고 새로 만들어주면 됨.
		board.setTitle("");
		List<WebReply> replies=replyRepo.findByBoardOrderByRnoDesc(board);
		return new ResponseEntity<List<WebReply>>(replies, status);
	}
	//삭제
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<List<WebReply>> deleteReply(@PathVariable("bno") Long bno,
			@PathVariable("rno") Long rno){
		replyRepo.deleteById(rno);
		return makeReturn(bno, HttpStatus.OK);
	}
	
	//수정
	@PutMapping("/{bno}")
	public ResponseEntity<List<WebReply>> updateReply(@RequestBody WebReply reply,
			@PathVariable("bno") Long bno){
		WebBoard board = boardRepo.findById(bno).get();
		reply.setBoard(board);
		replyRepo.save(reply);
		
		/*
		 * List<WebReply> wlist= replyRepo.findByBoardOrderByRnoDesc(board); return
		 * wlist;
		 */
		return makeReturn(bno,HttpStatus.OK);
	}
	
	
	@PostMapping("/{bno}")
	public ResponseEntity<List<WebReply>> insertReply(@RequestBody WebReply reply,
			@PathVariable("bno") Long bno){
		WebBoard board = boardRepo.findById(bno).get();
		reply.setBoard(board);
		//List<WebReply> wlist= replyRepo.findByBoardOrderByRnoDesc(board);
		replyRepo.save(reply);
		//System.out.println("bno>>>>"+bno+" reply>>"+reply);
		//return wlist;
		return makeReturn(bno, HttpStatus.CREATED);
	}
	
	@GetMapping("/{bno}")	//@PathVariable -> getMapping의 bno를 가져와서 쓰겠따
	public List<WebReply> selectAllReply(@PathVariable("bno") Long bno) {
		System.out.println(bno+ "=====모든 댓글 조회=====");
		WebBoard board = new WebBoard();
		board.setBno(bno);
		List<WebReply> replies=replyRepo.findByBoardOrderByRnoDesc(board); //board의 id로 조회
		
		//댓글들을 리턴시킴
		return replies;
	}
}
