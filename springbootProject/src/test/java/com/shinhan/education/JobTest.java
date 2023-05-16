package com.shinhan.education;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.shinhan.education.SpringbootProjectApplication;
import com.shinhan.education.repository.JobRepository;
import com.shinhan.education.vo.JobVO;


@SpringBootTest
@ContextConfiguration(classes = SpringbootProjectApplication.class)
public class JobTest {

	@Autowired
	JobRepository jobrepo;
	
	//@Test crud delete
	public void test6() {
		jobrepo.deleteAll();
	}
	
	//@Test crud delete
	public void test5() {
		jobrepo.findById("직책코드10").ifPresent(job->{
			jobrepo.delete(job);
		});
	}
	
	//@Test
	public void test4() {
	//crud update
		jobrepo.findById("직책코드10").ifPresent(job->{
			job.setJobtitle("마켓팅-수정");
			job.setMaxsalary(900000);
			job.setMinsalary(10000);
			jobrepo.save(job);
		});
	}
	
	@Test
	public void test3() {
		//CRUD read
		Optional<JobVO> jobOptional= jobrepo.findById("직책코드10");
		//optional을 사용할때 없을수도 있으니까 있냐고 꼭 물어봐야함.
		if(jobOptional.isPresent()) {
			JobVO job= jobOptional.get();
			System.out.println(job);
		}else {
			System.out.println("존재하는 직책이 없음!");
		}
		JobVO job= jobrepo.findById("직책코드10").orElse(null);
		if(job==null) {
			System.out.println("존재하는 직책이 없음!");
		}else {
			System.out.println(job);
		}
	}
	//@Test 전부 조회
	public void test2() {
		//crud
		Iterable<JobVO> datas = jobrepo.findAll();
		List<JobVO> joblist = (List<JobVO>) datas;
		if(joblist.size()==0) System.out.println("조회데이터없음");
		for(JobVO job:joblist) {
			System.out.println(job);
		}
	}
	
	
	//@Test //직원등록 crud create
	void test() {
		
		String[] arr = {"마케팅","SI개발자","SM개발자","기획자","팀장","차장","사원","과장"
				,"zz","gg"};
		
		
		IntStream.rangeClosed(1, 10).forEach(i->{
			JobVO job= JobVO.builder()
					.jobid("직책코드"+i)
					.jobtitle("직책"+arr[i-1])
					.maxsalary(90000)
					.minsalary(1000)
					.build();
			jobrepo.save(job);
		});
	}
	
	/*
	 * @Test //직원등록 void test() {
	 * 
	 * }
	 */
}
