package com.shinhan.education;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.MemberRepository;
import com.shinhan.education.repository.ProfileRepository;
import com.shinhan.education.vo.MemberDTO;
import com.shinhan.education.vo.MemberRole;
import com.shinhan.education.vo.ProfileDTO;

import lombok.extern.java.Log;

@SpringBootTest
@Log
public class ManyToOneTest {
	@Autowired
	MemberRepository mrepo;
	@Autowired
	ProfileRepository pRepo;

	//member 추가
	@Test
	void addMember() {
		IntStream.range(1, 4).forEach(i->{
			MemberDTO member = MemberDTO.builder()
					.mname("매니저--"+i)
					.mid("manager--"+i)
					.mpassword("8888")
					.mrole(MemberRole.ADMIN)
					.build();
			mrepo.save(member);
		});
		
	}
	//멤버의 profile 갯수 얻기
	//@Test
	public void getProfileCount() {
		//arr이 배열이라서 배열로 찍기
		List<Object[]> result = pRepo.getMemberWithProfileCount("user");
		result.forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}
	
	
	// 해당profile의 Member정보 알아내기
	//@Test
	void getMemberByProfile() {
		Long pno = 100L;
		pRepo.findById(pno).ifPresent(p -> {
			MemberDTO member = p.getMember();
			log.info(p.isCurrentYn() + ":" + member.getMname() + "----" + member.getMrole());
		});
	}

	// 특정멤버의 profile조회하기
	// 멤버가 profile을 알 수 없음! profile은 member를 앎
	// profileDTO에 MemberDTO가 잇으므로 findByMember만 정의하면 조회할 수 잇음
	@Test
	void ReadprofileTest() {
		//특정 멤버 가져오기
		MemberDTO member = mrepo.findById("user1").orElse(null);
		//구현해놓은 profile repository에서 가져오기
		pRepo.findByMember(member).forEach(a -> {
			log.info(a.toString());
		});

	}

	// @Test
	void profileInsertTest() {
		// 'user1'의 profile5건 입력
		MemberDTO member = mrepo.findById("user1").orElse(null);
		MemberDTO member2 = mrepo.findById("user2").orElse(null);
		if (member != null) {
			log.info(member.toString());
			IntStream.range(1, 6).forEach(i -> {
				ProfileDTO profile = ProfileDTO.builder().fname("face-" + i + ".jpg").currentYn(i == 5 ? true : false)
						.member(member).build();
				pRepo.save(profile);
			});
		}

		if (member2 != null) {
			log.info(member2.toString());
			IntStream.range(1, 6).forEach(i -> {
				ProfileDTO profile = ProfileDTO.builder().fname("hair-" + i + ".jpg").currentYn(i == 5 ? true : false)
						.member(member).build();
				pRepo.save(profile);
			});
		}
		pRepo.findAll().forEach(profile -> log.info(profile.toString()));

	}

	// @Test
	void memberSelectAll() {
		mrepo.findAll().forEach(member -> {
			System.out.println(member);
		});
	}

	// Member table에 10명 입력하기
	// @Test
	void memberInsert() {
		IntStream.range(1, 11).forEach(i -> {
			MemberDTO mem = MemberDTO.builder().mid("user" + i).mname("멤버" + i).mpassword("1234")

					.build();
			if (i <= 5) {
				mem.setMrole(MemberRole.ADMIN);

			} else {
				mem.setMrole(MemberRole.USER);
			}
			System.out.println(mem);
			mrepo.save(mem);
		});
	}

}
