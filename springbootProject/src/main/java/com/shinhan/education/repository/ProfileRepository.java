package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.vo.MemberDTO;
import com.shinhan.education.vo.ProfileDTO;

public interface ProfileRepository extends CrudRepository<ProfileDTO, Long>{
	List<ProfileDTO> findByMember(MemberDTO member);
	
	//select하는 컬럼이 두개이상이므로 Object[]로 받는다
	//JPQL
	@Query("select m.mid, count(p) "
			+ "from MemberDTO m left outer join ProfileDTO p on (m.mid = p.member) "
			+ "where m.mid like %?1%"
			+ "group by m.mid")
	public List<Object[]> getMemberWithProfileCount(String mid);
	//첫번쨰 parameter가 들어옴
}
