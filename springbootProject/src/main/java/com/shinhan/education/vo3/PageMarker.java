package com.shinhan.education.vo3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

@Getter
@ToString(exclude = "pageList")
@Log
public class PageMarker<T> {

	private Page<T> result;//어떤 vo인지는 t로 들어옴
	private Pageable prevPage; //이전으로 이동하는데 필요한 정보를 가짐
	private Pageable nextPage;
	private Pageable currentPage;
	private int currentPageNum;  //화면에 보이는 1부터 시작하는 페이지번호
	private int totalPageNum;//전체 페이지가 몇 개 있는지
	private List<Pageable> pageList;
	
	public PageMarker(Page<T> result,int pageSize) {
		this.result = result;
		this.currentPage = result.getPageable();//getPageable현재 페이지
		this.currentPageNum = currentPage.getPageNumber()+1;
		this.totalPageNum = result.getTotalPages();
		this.pageList = new ArrayList<Pageable>();
		calcPage(pageSize);
	}
	public void calcPage(int cnt) {
		int tempEndNum = (int)(Math.ceil(currentPageNum/(cnt*1.0)*cnt));
		int startNum = tempEndNum - (cnt-1);//10이면 91~100
		
		Pageable startPage = this.currentPage;
		for(int i = startNum; i<this.currentPageNum; i++) {//페이지 몇개 있는지 계산
			startPage = startPage.previousOrFirst();
		}
		this.prevPage = startPage.getPageNumber()<=0?null:startPage.previousOrFirst();
		log.info("tempEndNum:" + tempEndNum);
		log.info("totalPageNum:" + totalPageNum);
		if(this.totalPageNum<tempEndNum) {
			tempEndNum = this.totalPageNum;
			this.nextPage = null;
		}
		
		for(int i = startNum; i<=totalPageNum; i++) {
			pageList.add(startPage);
			startPage = startPage.next();
		}
		this.nextPage = startPage.getPageNumber()+1 < totalPageNum?startPage:null;
	}
}
