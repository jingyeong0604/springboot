package com.shinhan.education.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity

@Table(name = "tbl_pdsfiles")
@EqualsAndHashCode(of = "fno") //fno가 같으면 같음
public class PDSFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // 시퀀스를 사용하겠다.
	private Long fno;
	private String pdsfilename;
}
