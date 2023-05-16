package com.shinhan.education;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.DeptVORepository;
import com.shinhan.education.repository.EmpVORepository;

@SpringBootTest
public class EmpDeptTest {

	@Autowired
	EmpVORepository eRepo;
	
	@Autowired
	DeptVORepository dRepo;
	
	@Test
	void test1() {
		
	}
}
