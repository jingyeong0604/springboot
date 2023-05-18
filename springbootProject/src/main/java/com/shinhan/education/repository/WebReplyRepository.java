package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shinhan.education.vo3.WebBoard;
import com.shinhan.education.vo3.WebReply;


public interface WebReplyRepository extends PagingAndSortingRepository<WebReply, Long>{
	//댓글에서 board번호를 통해 댓글을 찾는 method이므로 새로 만들어야함.
	public List<WebReply> findByBoardOrderByRnoDesc(WebBoard board);
}
