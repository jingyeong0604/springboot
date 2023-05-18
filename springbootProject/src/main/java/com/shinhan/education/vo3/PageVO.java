package com.shinhan.education.vo3;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor @Builder
@ToString
public class PageVO {
	private static final int DEFAULT_SIZE = 10;//한페이지에 10개
	private static final int DEFAULT_MAX_SIZE = 50;	
	private int page;
	private int size;	
	//검색조건처리위해 field추가
	private String keyword;
	private String type;		
	public PageVO() {
		this.page = 1;
		this.size = DEFAULT_SIZE;
	}
	
	//setter의 동작이 다르기때문에 setter를 직접 만들어준다.
	public void setSize(int size) {
		this.size = size<DEFAULT_SIZE || size > DEFAULT_MAX_SIZE?
				DEFAULT_SIZE:size;
	}
	//매개변수 첫번째: asc, desc를 설정 0이면 desc 1이면 asc
	//pageable이 return 됨
	public Pageable makePageable(int direction, String ...prop) {
		Sort.Direction dir = direction==0?Sort.Direction.DESC:Sort.Direction.ASC;
		return PageRequest.of(this.page-1, this.size, dir, prop);
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setType(String type) {
		this.type = type;
	}
}
