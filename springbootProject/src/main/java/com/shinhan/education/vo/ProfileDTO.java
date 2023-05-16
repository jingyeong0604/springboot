package com.shinhan.education.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
@EqualsAndHashCode(of = {"pno"} )
@Builder
@AllArgsConstructor
@NoArgsConstructor  
@Entity
@Table(name="tbl_profile")
public class ProfileDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pno;
	private String fname;
	private boolean currentYn;
	//false:0  true:1 //default가 not null임
	
	@ManyToOne//참조하는쪽에서 칼럼이 생김 
	private MemberDTO member; //member_mid칼럼이 DB생성된다. 
	
	
	//직원, 부서 
	//직원이 부서를 참조한다. (n이 참조한다!) 즉 ProfileDTO(n)이 MemberDTO(1)멤버의 키를 참조함.
	//직원테이블에 부서의 키를 FK로 생성한다. 
	
	//ProfileDTO가 MemberDTO를 참조한다.
	//tbl_profile테이블에 tbl_members테이블의 키값 mid가 FK로 생성한다. 
	
}