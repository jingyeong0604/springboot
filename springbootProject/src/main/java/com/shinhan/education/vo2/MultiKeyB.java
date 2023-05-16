package com.shinhan.education.vo2;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor @AllArgsConstructor
@Builder
@Data
@Embeddable
public class MultiKeyB implements Serializable{
	
	private Integer id1;
	private Integer id2;
}
