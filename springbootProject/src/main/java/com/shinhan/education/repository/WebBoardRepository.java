package com.shinhan.education.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.shinhan.education.vo3.QWebBoard;
import com.shinhan.education.vo3.WebBoard;

public interface WebBoardRepository extends PagingAndSortingRepository<WebBoard, Long>,QuerydslPredicateExecutor<WebBoard>{

	//1.상수
	//2.추상메서드
	//3.default 메서드
	//4.static 메서드
	//type이 뭐냐에 따라 keyword를 넣고 싶음
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QWebBoard board = QWebBoard.webBoard;
		builder.and(board.bno.gt(0)); //and bno>0라는 조건이 동적으로 들어감.
		//검색조건처리
		if(type==null) return builder;
		switch (type) { //and title like?
		case "title": 
			builder.and(board.title.like("%" + keyword + "%")); break;
		case "content": //and content like?
			builder.and(board.content.like("%" + keyword + "%")); break;
		//case "w": builder.and(board.writer.like("%" + keyword + "%")); break;
		default: break;
		}
		return builder;
	}
}
