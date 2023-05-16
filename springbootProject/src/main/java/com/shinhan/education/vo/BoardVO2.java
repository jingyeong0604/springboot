package com.shinhan.education.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

//@Entity:jpa(db와 매핑해주는 역할, vo를 앤티티로 만듦)가 관리함
@Getter@Setter@ToString

@Entity
@Table(name="t_boards2")
public class BoardVO2 {
	@Id//기본키값(유일한값)
	@GeneratedValue(strategy = GenerationType.AUTO)//자동으로 기본키 생성
	private Long bno;
	
	@NonNull//생성시 반드시 값이 있어야 한다.(lombok)->java를 의미함.
	@Column(nullable=false)//db의 칼럼이 not null
	private String title;
	@Column(length = 100)
	private String content;
	
	private String writer;
	@CreationTimestamp //insert시 시각이 입력
	private Timestamp regdate;
	@UpdateTimestamp //update시 수정시각입력
	private Timestamp updatedate;
	
	
}
