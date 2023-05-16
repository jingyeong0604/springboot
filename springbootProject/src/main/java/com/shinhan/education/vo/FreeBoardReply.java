package com.shinhan.education.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString(exclude="board") 
@Entity @EqualsAndHashCode(of = {"rno"})//rno가 같으면 같다
@Table(name="tbl_free_replies")
public class FreeBoardReply {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rno;
	private String reply;
	private String replyer;
	@CreationTimestamp
	private Timestamp regDate;
	@UpdateTimestamp
	private Timestamp updateDate;
	
	//연관관계 설정->n:1
	//FK: tbl_freeboards에 칼럼이 board_bno로 생성된다.
	@ManyToOne
	FreeBoard board;
	
}
