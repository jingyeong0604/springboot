package com.shinhan.education.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//t_jobs table만들기 
@Getter @Setter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@Data
@Table(name="t_jobs")
public class JobVO {
	
	@Id
	private String jobid;
	@Column(unique=true, nullable=false, length=35)
	private String jobtitle;
	private int maxsalary; 
	private int minsalary;
	@CreationTimestamp//입력시
	private Timestamp regdate;
	@UpdateTimestamp//입력시+ 수정시
	private Timestamp updatedate;
}
