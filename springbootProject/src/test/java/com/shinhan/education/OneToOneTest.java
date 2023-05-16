package com.shinhan.education;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.UserCellPhoneVORepository;
import com.shinhan.education.repository.UserCellPhoneVORepository2;
import com.shinhan.education.repository.UserVO3Repository;
import com.shinhan.education.repository.UserVORepository;
import com.shinhan.education.vo2.UserCellPhoneVO;
import com.shinhan.education.vo2.UserCellPhoneVO2;
import com.shinhan.education.vo2.UserCellPhoneVO3;
import com.shinhan.education.vo2.UserVO;
import com.shinhan.education.vo2.UserVO2;
import com.shinhan.education.vo2.UserVO3;

@SpringBootTest
public class OneToOneTest {

	@Autowired
	UserVORepository uRepo;

	@Autowired
	UserCellPhoneVORepository pRepo;

	@Autowired
	UserCellPhoneVORepository2 pRepo2;

	@Autowired
	UserVO3Repository uRepo3;
	
	@Test
	void test3() {
		
		UserCellPhoneVO3 phone = UserCellPhoneVO3.builder()
				.phoneNumber("010-1112-1111")
				.model("아이폰xs")
				.build();
		
		
		UserVO3 user = UserVO3.builder()
				.userid("user1")
				.username("jingyeong")
				.phone(phone)
				.build();
		phone.setUser(user);
		uRepo3.save(user);
		
	}
	
	//@Test
	void test2() {
		UserVO2 user = UserVO2.builder()
				.userid("good")
				.username("홍대")
				.build();

		UserCellPhoneVO2 phone = UserCellPhoneVO2.builder()
				.phoneNumber("010-2111-1111")
				.model("아이폰13")
				.user(user)
				.build();
		pRepo2.save(phone);
	}

	// @Test
	void test1() {
		UserCellPhoneVO phone = UserCellPhoneVO.builder().phoneNumber("010-2122-1111").model("아이폰13").build();
		// 폰이 있어야 user에서 그 폰을 참조할 수 있음.
		UserCellPhoneVO savedPhone = pRepo.save(phone);

		UserVO user = UserVO.builder().userid("good").username("홍대").phone(savedPhone).build();
		uRepo.save(user);
	}

}
