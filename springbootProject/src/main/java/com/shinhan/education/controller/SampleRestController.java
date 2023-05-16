package com.shinhan.education.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.education.repository.BoardRepository;
import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.vo.BoardVO;
import com.shinhan.education.vo.CarVO;
import com.shinhan.education.vo.QBoardVO;

import lombok.extern.java.Log;

@Log
@RestController //@Controller+ @ResponseBody
//jsp에서는 response.getWriter().append("jsp/servlet에서") 문자가 내려가는 것
public class SampleRestController {
	
	@Autowired
	BoardRepository brepo;
	
	@Autowired
	PDSBoardRepository boardRepo;
	
	@GetMapping("/monday")
	@Transactional //@Modifying을 사용한 경우 반드시 @Transactional사용
	 //실행은 성공하지만 test 환경이므로 rollback처리가 됨->원래대로 돌아감. (변경되지가 않음) 
	//sol) class level에 커밋 추가하기!
	String fileUpdate() {
		int result = boardRepo.updateFile(5L, "풍경사진^0^");	
		return "OK>>"+result;
	}
	
	
	
	
	@GetMapping("/sunday")
	public List<BoardVO> dynamicSQLTest() {
		String title="게시글 제목";//and title like '%제목9%'
		Long bno=1L;//and bno>150
		
		BooleanBuilder builder=new BooleanBuilder();
		QBoardVO board = QBoardVO.boardVO;
		builder.and(board.title.like("%"+title+"%"));
		builder.and(board.bno.gt(bno));//bno>150
		builder.and(board.writer.eq("작성자9"));//작성자 9번에 대한거승ㄹ 출력
		//System.out.println(builder);
		//findAll()=>CrudRepository에서 제공됨  
		//findAll(predicate)=>QuerydslPredicateExecutor에서 제공됨
		//predicate란???? 맞았는지 틀렸는지 
		List<BoardVO> blist = (List<BoardVO>) brepo.findAll(builder);
		blist.forEach(arr->{
			log.info(arr.toString());
		});
		return blist;
		
	}
	
	
	
	
	@GetMapping("/friday")
	public Map<String, Object> sample1() {
		long rowCount =brepo.count();
		log.info(rowCount+"건");
		//브라우저에 요청하고 싶으면 컨트롤러에 추가하면됨 지금은 테스트과정
		
		boolean result = brepo.existsById(100L);
		log.info(result?"존재한다":"존재하지 않는다");
		
		Map<String, Object> map=new HashMap<>();
		map.put("aa", rowCount+"건");
		map.put("bb", result?"존재한다":"존재하지 않는다");
		//BoardVO boardvo=new BoardVO();
		
		map.put("data", brepo.findById(299L).orElse(null));
		
		return map;//responsebody형태로 body에 내용을 보냄
		
	}
	
	
	
	
	@GetMapping("/cartest")
	public List<CarVO> test3() { //Jackson이 JAVA 객체를 JSON으로 만들어서 return
		List<CarVO> carlist = new ArrayList<>();
		IntStream.rangeClosed(1, 10).forEach(index->{
			CarVO car1 = CarVO.builder()
					.model("BMW520-----<"+index+">")
					.price(6000)
					.build();
			carlist.add(car1);
		});
		
	
		return carlist;
		
	}
	
	//@RequestMapping(value= "/sample1", method= RequestMethod.GET)
	@GetMapping("/sample1")
	public String test1() {
			return "SpringBoot 열공";
	}
	
	@GetMapping("/sample2")
	public String test2() {
			return "SpringBoot ---잘되는지 확인입니다~";
	}
}
