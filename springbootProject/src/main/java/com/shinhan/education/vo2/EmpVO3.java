package com.shinhan.education.vo2;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor
@Getter@Setter
@ToString
@Entity
@Table(name="tbl_emp3")
//JavaBeans: 변수 접근지정자는 private, setter/getter, 기본생성자 
public class EmpVO3 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="employee_id")
	 private int employee_id;  
	
	 private String first_name;                                         
	 private String last_name;                              
	 private String email ;                                    
	 private String phone_number;                                    
	 private Date hire_date ;                                
	 private String job_id;                                    
	 private double salary ;                                           
	 private double commission_pct;                                     
	 private int manager_id;                                        
	 private int department_id;
	 
//	 departments 1->tbl_dept OneToMany
//	 employeees n->tbl_emp manyToOne 
	 
	 @ManyToOne
	 DeptVO2 dept;
	 
	 
	 
	 
}
