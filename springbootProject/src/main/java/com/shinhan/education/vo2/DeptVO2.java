package com.shinhan.education.vo2;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name="tbl_dept2")
public class DeptVO2 {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 int department_id;
	 
	 String department_name;
	 int manager_id;
	 int location_id;
	 
	 @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	 @JoinColumn(name="department_id")
	 List<EmpVO2> emplist;
	 
	 
	 
}
