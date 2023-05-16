package com.shinhan.education.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@ToString//files2를 제외시키고 출력함.(exclude="files2")
@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "tbl_pdsboard")
@EqualsAndHashCode(of = "pid") // of가 있으면 모든 칼럼이 같다
public class PDSBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pid;
	private String pname;
	
	private String pwriter;
	//CascadeType.ALL
	@OneToMany(cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER)//EAGER즉시 로딩//나를 조회하면(PDSBoard) PDSFile도 자동 조회됨.
	//LAZY join하고 있는 
	//JPA는 default로 지연로딩 사용 pdsboard조회시 pdsfile 조인하지 않음.
	//1. lazy pdsfile과 연결불가 @Query로 해결
	//2. eager pdsfile과 연결가능->join된 테이블 값을 가져올 수 있음.
	@JoinColumn(name = "pdsno") // PDSFile칼럼에 pdsno가 생성->중간 테이블이 생성되지 않고 pdsno 칼럼이 생김.
	private List<PDSFile> files2;//n이 여기로 들어옴, file2변수는 자바에서 칼럼으로 생성되지 않음.
}
