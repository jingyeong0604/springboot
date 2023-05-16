package com.shinhan.education.vo2;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity @Getter @Setter
@Table(name="tbl_usercellphone3")

public class UserCellPhoneVO3 {
	
	//식별자로 참조하기->참조하는 키를 pk로 사용한다!
	@Id
	String userid;

	@MapsId//FK를 PK로 지정할 때 사용하는 어노테이션이다.
	@OneToOne
	@JoinColumn(name="phone_id")
	UserVO3 user;
	
	String phoneNumber;
	String model;
}
