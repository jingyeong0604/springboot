package com.shinhan.education;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.MultiKeyARepository;
import com.shinhan.education.repository.MultiKeyBRepository;
import com.shinhan.education.vo2.MultiKeyA;
import com.shinhan.education.vo2.MultiKeyADTO;
import com.shinhan.education.vo2.MultiKeyB;
import com.shinhan.education.vo2.MultiKeyBDTO;


@SpringBootTest
public class MultikeyTest {

	@Autowired
	MultiKeyARepository multiRepo;
	
	@Autowired
	MultiKeyBRepository bRepo;
	
	@Test
	public void test2() {
		MultiKeyB id= MultiKeyB.builder()
				.id1(10)
				.id2(20)
				.build();
		MultiKeyBDTO b = MultiKeyBDTO.builder()
				.id(id)
				.userName("사용자1")
				.address("제주도")
				.build();
		bRepo.save(b);
	}
	
	//insert해서 넣기
	//@Test
	public void test1() {
		MultiKeyADTO a= new MultiKeyADTO();
		a.setId1(1);
		a.setId2(2);
		a.setUserName("jin1");
		a.setAddress("busan");
		multiRepo.save(a);
	}
	
	
}
