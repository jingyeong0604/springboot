package com.shinhan.education.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shinhan.education.vo.JobVO;


public interface JobRepository extends CrudRepository<JobVO, String> {

}
