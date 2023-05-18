package com.shinhan.education.vo3;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity @Builder
@Data
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Table(name="tbl_webboard")
@EqualsAndHashCode(of = "bno") 
public class WebBoard {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	Long bno;
	@NonNull //WebBoard가 build될때 반드시 setting해야한다.
	@Column(nullable=false)//db칼럼이 not null(필수 칼럼이다)
	String title;
	String content;
	String writer;
	@CreationTimestamp
	Timestamp regdate;
	@UpdateTimestamp
	Timestamp updatedate;
	
	@JsonIgnore
	@BatchSize(size=100) //원래는 db에서 select를 100번 출력할것을
	//한 번에 100만큼 가져옴.
	@OneToMany(mappedBy="board", fetch=FetchType.EAGER,
			cascade = CascadeType.ALL)
	private List<WebReply> replies;
	
	
}
