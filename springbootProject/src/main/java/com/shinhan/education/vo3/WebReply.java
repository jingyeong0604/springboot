package com.shinhan.education.vo3;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

@Getter @Setter @Entity @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name="tbl_webreplies")
@EqualsAndHashCode(of="rno")

public class WebReply {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long rno;
	String replyText;
	String replyer;
	@CreationTimestamp
	Timestamp regdate;
	@UpdateTimestamp
	Timestamp updatedate;
	
	@JsonIgnore//board까지 올필요 없으므로 무시함.
	@ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private WebBoard board;
}
