package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shinhan.education.vo.BoardVO;

@Repository
//인터페이스 설계-> crud작업을 하기 위해서 sql 구현은 jpa가 한다.(구현체는 안보임 우리는 설계만함)
//1. 기본은 :findAll(), findById(), save()
//2. 규칙에 맞는 메서드 추가:findByXXXXXX
//3.JPQL 사용:@Query
//4.Querydsl 사용:@Query, nativQuery = true를 사용하여 직접 SQL 작성 가능
//key는 Long타입
public interface BoardRepository extends CrudRepository<BoardVO, Long>,
								QuerydslPredicateExecutor<BoardVO>
				{
	
	//조건조회 추가하기
	public List<BoardVO> findByTitle(String title);//title이 where절에 들어가는 것
	public List<BoardVO> findByContent(String aa);
	public List<BoardVO> findByWriter(String writer);
	public List<BoardVO> findByWriterAndTitle(String writer, String title);//앞이 writer, 뒤가 title
	
	public List<BoardVO> findByTitleContaining(String title);//select* from t_boards where title like '%?%'
	public List<BoardVO> findByTitleStartingWith(String title);//where title like '%?%'
	public List<BoardVO> findByTitleEndingWith(String title);//where title like '%?%'
	
	public List<BoardVO> findByTitleContainingAndBnoGreaterThanEqual(String title, int bno);
	//public List<BoardVO> findByTitleContainingAndBnoGraterThanEqual(String title, Long bno);
	
	public List<BoardVO> findByBnoBetweenOrderByBno(Long bno1, Long bno2);
	public List<BoardVO> findByContentIgnoreCase(String content);
	//where upper(Content)=? 가 같은지!
	
	
	//where Title like? order by Title desc
	public List<BoardVO> findByTitleContainingOrderByTitleDesc(String title);
	
	//타이틀로 조회, sort desc, paging =:page, size
	public List<BoardVO> findByTitleContainingOrderByTitleDesc(String title,Pageable paging);

	public List<BoardVO> findByTitleContaining(String title,Pageable paging);
	
	//전체 건수를 알아야함. Page로 리턴
	public Page<BoardVO> findByBnoGreaterThan(Long bno,Pageable paging);

	
	//JPQL(JPL Query Language)규칙을 지켜 함수 만들기가 어려워서 나옴
	//..*지원하지 않음 BoardVO는 entity 자리임
	@Query("select b from BoardVO b where b.title like %?1%"
			+ " and b.content like %?2% order by b.bno desc")
	public List<BoardVO> findByTitle2(String title, String content);
	
	//param을 사용해서 변수의 순서를 맞춰주지 않아도 됨.
	@Query("select b from BoardVO b where b.title like %:tt%"
			+ " and b.content like %:cc% order by b.bno desc")
	public List<BoardVO> findByTitle3(@Param("tt") String title,@Param("cc") String content);

	@Query("select b from #{#entityName} b where b.title like %?1%"
			+ " and b.content like %?2% order by b.bno desc")
	public List<BoardVO> findByTitle4(String title, String content);
	
	//몇개의 컬럼만 가져올 경우 List<Object[]> 오브젝트 배열로 가져오기.
	@Query("select b.title, b.content, b.writer from #{#entityName} b where b.title like %?1%"
			+ " and b.content like %?2% order by b.bno desc")
	public List<Object[]> findByTitle5(String title, String content);
	
	//JPQL(JPL Query Language)..SQL문을 직접 작성하는 경우 nativeQuery=true라고만 작성하면 됨(남용하지 않기)
	@Query(value = "select * from t_boards where title like '%'||?1||'%'"
			+ " and content like '%'||?2||'%' order by bno desc", nativeQuery=true)
	public List<BoardVO> findByTitle6(String title, String content);
	
}
