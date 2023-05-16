package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.vo.FreeBoard;
import com.shinhan.education.vo.FreeBoardReply;

//findAll(), findById(댓글번호가 key)
public interface FreeRepiesRepository extends CrudRepository<FreeBoardReply, Long> {

	//댓글이 여러개 와야함 Board로 조회하고 싶음!
	List<FreeBoardReply> findByBoard(FreeBoard board);
	
}
