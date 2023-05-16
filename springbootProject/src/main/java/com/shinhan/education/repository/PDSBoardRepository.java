package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.shinhan.education.vo.PDSBoard;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long>{
	//연결고리가 없으면 이렇게 사용할 수 있음.
	//LAZY fetch였는데 Eager로 사용해야할때 JPQL를 이용함.
	@Query("select b.pname, b.pwriter, f.pdsfilename "
			+ " from PDSBoard b left outer join b.files2 f "
			+ " where b.pid = ?1 order by b.pid ")
	public List<Object[]> getFilesInfo(long pid);
	
	//몇건인지 출력 fetch=FetchType.LAZY인 경우 nativeQuery를 사용할 수 있음.
	@Query(value="select board.pname,count(*) from tbl_pdsboard board"
			+ " left outer join tbl_pdsfiles pdsfiles"
			+ " on(board.pid = pdsfiles.pdsno) group by board.pname",nativeQuery=true)
	public List<Object[]> getFilesInfo2();
	
	//board를 통해서 파일로 가서 수정->update문이므로 query+modifying
	//@Query는 select 만 가능, DML 사용시 Modifying을 반드시 함꼐 사용함. 
	@Modifying 
	//@Transactional//여기에 있으면 commit된다. ->transacitonal은 service에 위치하는게 바람직함.  
	@Query("update from PDSFile f set f.pdsfilename=?2 where f.fno=?1")//?1 파라미터 첫번째
	int updateFile(Long fno, String newFileName);

}
