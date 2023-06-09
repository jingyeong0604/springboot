package com.shinhan.education.vo2;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Getter @Setter
@Table(name="tbl_user3")
public class UserVO3 {
	
	@Id
	@Column(name="user_id")
	String userid;
	String username;
	
	
	//양방향! 주테이블의 키를 식별자로 사용하는 경우!
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	UserCellPhoneVO3 phone;
	
	
	
}
