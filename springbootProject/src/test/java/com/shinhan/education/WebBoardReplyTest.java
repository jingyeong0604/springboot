package com.shinhan.education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo3.WebBoard;
import com.shinhan.education.vo3.WebReply;

@SpringBootTest
public class WebBoardReplyTest {
	@Autowired
	WebBoardRepository boardRepo;
	
	@Autowired
	WebReplyRepository replyRepo;
	//board에 insert 100건
	//@Test
	void test1() {
	List<WebReply> replies=new ArrayList<WebReply>();
	IntStream.rangeClosed(300, 400).forEach(a->{
		WebBoard entity = WebBoard.builder()
				.title("제목"+(a%10))
				.content("내용"+(a%10))
				.build();
		boardRepo.save(entity);
		});
		
				
	
	}
	
	//5개의 board에 댓글 10개 넣기
	//@Test
	void test2() {
		Long[] arr= {141L, 142L, 143L, 144L};
		Arrays.stream(arr).forEach(bno->{
			boardRepo.findById(bno).ifPresent(board->{
				IntStream.rangeClosed(20, 30).forEach(indx->{
					WebReply reply=WebReply.builder()
							.replyText("댓글"+indx)
							.replyer("작성자"+indx)
							.board(board)
							.build();
					replyRepo.save(reply);
				});
			});
		});
	}
	
	//특정 board 조회
	//@Test
	void test3() {
		boardRepo.findById(141L).ifPresent(a->{
			System.out.println();
		});
	}
	
	//모든 board 조회
	//@Test
	void test4() {
		boardRepo.findAll().forEach(a->{
			System.out.println(a);
		});
	}
	
	
	//특정 board 댓글(Board1에서 시작)
	@Test
	void test5() {
		boardRepo.findById(141L).ifPresent(board->{
			List<WebReply> list=board.getReplies();
			for(WebReply reply: list) System.out.println(reply);
		});
	}
	
	//특정 board댓글(Reply->N에서 시작)
	@Test
	void test6() {
		WebBoard board = boardRepo.findById(141L).get();
		List<WebReply> list = replyRepo.findByBoard(board);
		list.forEach(reply->{
			System.out.println(reply);
		});
	}
	//board별 댓글수
}
