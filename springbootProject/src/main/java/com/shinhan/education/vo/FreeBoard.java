package com.shinhan.education.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@ToString//댓글->보드 무한 루프 반복 따라서 제외시켜줌
@Entity @EqualsAndHashCode(of = {"bno","title"})//bno, title이 같으면 같다
@Table(name="tbl_free_boards")
public class FreeBoard {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long bno;
	private String title;
	private String writer;
	private String content;
	@CreationTimestamp
	private Timestamp regDate;
	@UpdateTimestamp
	private Timestamp updateDate;
	
	//연관관계 설정 ->1:n
	@JsonIgnore //jackson이 json만들때 무시 toString(exclue)와 유사
	@OneToMany(cascade =CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="board")
	List<FreeBoardReply> replies;
}
