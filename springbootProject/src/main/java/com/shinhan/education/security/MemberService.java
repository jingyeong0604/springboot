package com.shinhan.education.security;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shinhan.education.repository.MemberRepository;
import com.shinhan.education.vo.MemberDTO;

@Service
public class MemberService implements UserDetailsService {
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private MemberRepository mrepo;

	@Autowired
	PasswordEncoder passwordEncoder; // security config에서 Bean생성, ->암호화할 수 있음.
	
    // 회원가입
	//@Transactional 
	public MemberDTO joinUser(MemberDTO member) {
        // 비밀번호 암호화...암호화되지않으면 로그인되지않는다.
		String pwd = passwordEncoder.encode(member.getMpassword());//멤버정보가 들어오면 패스워드 인코드
		//mpassword:1234->encode 과정으로 암호화 됨.
		
		member.setMpassword(pwd);
		System.out.println("암호화된 pass:" + pwd);
		return mrepo.save(member);
	}

    //!!!!반드시 구현해야한다. ->implements를 했기 떄문에
	//인증처리를 함
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername mid:" + mid);
		//filter는 조건에 맞는것만 선택
		//map: 변형한다. 

		UserDetails member = mrepo.findById(mid).filter(m -> m != null).map(m -> new SecurityUser(m)).get();
		System.out.println("UserDetails member:" + member);
		httpSession.setAttribute("user", member);//user정보를 session에 저장(페이지 이동해도 유저정보는 그대로 저장)
		httpSession.setAttribute("member", mrepo.findById(mid).get());//member정보가 들어가있음
		return member;
	}

}
