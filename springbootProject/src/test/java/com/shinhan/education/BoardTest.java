package com.shinhan.education;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.education.repository.BoardRepository;
import com.shinhan.education.vo.BoardVO;
import com.shinhan.education.vo.QBoardVO;

import lombok.extern.java.Log;


//JUNIT으로 단위 test하기
@SpringBootTest
@Log
class BoardTest {
	@Autowired
	BoardRepository brepo;
	Logger logger= LoggerFactory.getLogger(BoardTest.class);
	
	
	@Test
	void dynamicSQLTest() {
		String title="게시글 제목";//and title like '%제목9%'
		Long bno=80L;//and bno>150
		
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
	}
	
	//@Test
	void sample6NativQueryTest() {
		List<BoardVO> blist = brepo.findByTitle6("제목", "내용");
		blist.forEach(arr->{
			log.info(arr.toString());
		});
	}
	
	//@Test //findByTitle3
	void sample7() {
		List<Object[]> blist = brepo.findByTitle5("제목", "내용");
		blist.forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}
	
	//@Test //findByTitle2
	void sample6() {
		List<BoardVO> blist =brepo.findByTitle2("제목", "내용");
		//List<BoardVO> blist =brepo.findByTitle3("제목", "내용");
		//List<BoardVO> blist =brepo.findByTitle4("제목", "내용");
		blist.forEach(board->{
			log.info(board.toString());
		});
	}
	
	//findByBnoGreaterThan
	//@Test
	void sample5() {
		Sort sort = Sort.by(Sort.Direction.DESC,new String[] {"writer","bno"});
		Pageable paging=PageRequest.of(0,5,sort);//(몇페이지인지, 한페이지 사이즈)
		
		Page<BoardVO> plist =brepo.findByBnoGreaterThan(1L,paging);
		log.info("페이지당 건수:"+ plist.getSize());
		log.info("페이지 총수:"+ plist.getTotalPages());
		log.info("페이지 전체건수:"+ plist.getTotalElements());//전체 건수 알수 있음.
		log.info("다음 페이지:"+plist.nextPageable());
		List<BoardVO> blist = plist.getContent();
		
		plist.forEach(board->{
			log.info(board.toString());
		});
	
	}
	
	
	//@Test
	void sample4() {
		//Sort sort=Sort.by("bno");//bno로 sort
		//sort.decending() 디센딩 소트
		
		//Order by writer DESC, bno DESC
		Sort sort = Sort.by(Sort.Direction.DESC,new String[] {"writer","bno"});
		Pageable paging=PageRequest.of(7,10,sort);//(몇페이지인지, 한페이지 사이즈)
		
		List<BoardVO> blist =brepo.findByTitleContaining("제목",paging);
		blist.forEach(board->{
			log.info(board.toString());
		});
	
	}
	//@Test
	void sample3() {
		Pageable paging=PageRequest.of(1,5);//(몇페이지인지, 한페이지 사이즈)
		
		List<BoardVO> blist =brepo.findByTitleContainingOrderByTitleDesc("제목",paging);
		blist.forEach(board->{
			log.info(board.toString());
		});
	
	}
	
	
	
	//where Title like? order by Title desc
	//@Test
	void sample2() {
		List<BoardVO> blist = brepo.findByTitleContainingOrderByTitleDesc("제목");
		blist.forEach(board->{
			log.info(board.toString());
		});
	}
	
	//@Test
	void sample1() {
		long rowCount =brepo.count();
		log.info(rowCount+"건");
		//브라우저에 요청하고 싶으면 컨트롤러에 추가하면됨 지금은 테스트과정
		
		boolean result = brepo.existsById(100L);
		log.info(result?"존재한다":"존재하지 않는다");
	}
	
	
	
	//@Test
	public void retrieve10() {
		List<BoardVO> blist = brepo.findByContentIgnoreCase("");
		blist.forEach(board->{
			System.out.println(board);
		});
	}
	
	//@Test
	public void retrieve9() {
		List<BoardVO> blist = brepo.findByBnoBetweenOrderByBno(55L,88L);
		blist.forEach(board->{
			System.out.println(board);
		});
	}
	//title like '%?%' and bno>?
	//@Test
	public void retrieve8() {
		List<BoardVO> blist = brepo.findByTitleContainingAndBnoGreaterThanEqual("게시글내용", 16);
		blist.forEach(board->{
			System.out.println(board);
		});
	}
	
	
	//@Test
	public void retrieve7() {
		List<BoardVO> blist = brepo.findByTitleEndingWith("37");
		blist.forEach(board->{
			System.out.println(board);
		});
	}
	//@Test
	public void retrieve6() {
		List<BoardVO> blist = brepo.findByTitleStartingWith("2");
		blist.forEach(board->{
			System.out.println(board);
		});
	}
	//@Test
	public void retrieve5() {
		List<BoardVO> blist = brepo.findByTitleContaining("제목1");
		blist.forEach(board->{
			System.out.println(board);
		});
	}
	
	//@Test
		public void retrieve4() {
			List<BoardVO> blist = brepo.findByWriterAndTitle("작성자6", "게시글 제목36");
			blist.forEach(board->{
				System.out.println(board);
			});
		}
	
	//@Test
	public void retrieve3() {
		List<BoardVO> blist = brepo.findByWriter("작성자0");
		blist.forEach(board->{
			System.out.println(board);
		});
	}
	
	
	@Test
	public void retrieve1() {
		List<BoardVO> blist = brepo.findByTitle("게시글 제목36");
		blist.forEach(board->{
			System.out.println(board);
		});
	}
	//@Test
	public void retrieve2() {
		List<BoardVO> blist = brepo.findByContent("게시글내용39");
		blist.forEach(board->{
			System.out.println(board);
		});
	}
	
	
	
	//@Test
	void test6() {
		BoardVO board = brepo.findById(1L).orElse(null);
		if(board!=null) {
			board.setContent("내용수정");
			board.setTitle("제목수정");
			board.setWriter("admin");
			brepo.save(board);//이미있는 데이터는 업데이트
		}
		System.out.println(board);
	}
	//@Test
	void test5() {
		/*
		 * brepo.findById(10L).ifPresent(board->{ System.out.println(board); });
		 */
		
		BoardVO board = brepo.findById(1L).orElse(null);
		System.out.println(board);
	}
	//@Test
	void test4() {
		brepo.findAll().forEach(board->{
			System.out.println(board);
		});
	}
	//@Test
	void test3() {
		IntStream.range(1, 100).forEach(i->{
			
			BoardVO board = BoardVO.builder()
					.title("게시글 제목"+i)
					.content("게시글내용" +i)
					.writer("작성자"+ i%10)
					.build();
			
			System.out.println(board);
			brepo.save(board);//save함수에 insert가 됨
		});
		
	}
	
//	@Test
//	void test2() {
//		//CarVO car1 = CarVO.builder().build(); //noArgs build
//		CarVO car1 = CarVO.builder()
//				.model("B모델")
//				.price(3000)
//				.build(); //함수 이름을 쓰지 않고 변수만 써서 사용할 수 있음.
//		logger.info(car1.toString());
//	}
//	@Test
//	void test1() {
//		CarVO car1 = new CarVO();
//		car1.setModel("A모델");
//		car1.setPrice(10000);
//	
//		
//		CarVO car2=new CarVO();
//		car2.setModel("A모델");
//		car2.setPrice(10000);
//		//logger.info(car1);
//		
//	}
	
	@Test
	void contextLoads() {
	}

}
