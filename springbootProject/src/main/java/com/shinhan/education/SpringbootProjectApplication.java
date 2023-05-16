package com.shinhan.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//project생성시 패키지의 하위에 있는 패키지는 자동스캔된다.
//@ComponentScan(basePackages= {"com.shinhan"})//com.shinhan으로 시작하면 다 스캔 
//@EntityScan("com.shinhan")
//@EnableJpaRepositories("com.shinhan")//respotory가 들어있는 곳을 자동으로 스캔해줘야함.
public class SpringbootProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProjectApplication.class, args);
	}

}
