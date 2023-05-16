package com.shinhan.education.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Data
@Builder //@NoArgsConstructor +@AllArgsConstructor 모두 추가
public class CarVO {
	 String model;
	 int price;
}
