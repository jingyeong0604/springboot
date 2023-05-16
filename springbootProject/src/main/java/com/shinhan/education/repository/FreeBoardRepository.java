package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.vo.FreeBoard;

public interface FreeBoardRepository extends CrudRepository<FreeBoard,Long>{
	//board번호가 100이상인 board조회..paging추가
	List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable paging);
}
