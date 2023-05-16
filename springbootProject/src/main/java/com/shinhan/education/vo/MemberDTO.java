package com.shinhan.education.vo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_members")//하나의 멤버가 여러개의 프로필을 갖는다.
public class MemberDTO {
	//member 한명에 profile 여러개
    @Id
	String mid;
	String mpassword;
	String mname;
	
	@Enumerated(EnumType.STRING)//enum으로 선언된 문자가 저장됐으면 좋겠다. 순서 상관없이!
	MemberRole mrole;
	
}
