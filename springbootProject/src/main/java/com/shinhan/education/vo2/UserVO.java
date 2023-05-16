package com.shinhan.education.vo2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name="tbl_user")
//주 테이블에 외래키 저장
public class UserVO {
	
	@Id
	@Column(name="user_id")
	String userid;
	String username;
	
	//주 테이블에서 참조하기!
	@OneToOne
	@JoinColumn(name="phone_id")
	UserCellPhoneVO phone;
	
	
}
