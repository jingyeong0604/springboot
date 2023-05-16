package com.shinhan.education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.repository.PDSFileRepository;
import com.shinhan.education.vo.PDSBoard;
import com.shinhan.education.vo.PDSFile;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class OneToManyTest {
	@Autowired
	PDSFileRepository fileRepo;
	@Autowired
	PDSBoardRepository boardRepo;
	
	//board별 file의 건수출력
	@Test
	public void test4() {
	
		//eager fetch
		boardRepo.findAll().forEach(board->{
			System.out.println(board.getPname()+"-->"+board.getFiles2().size());
		});
		
		//lazy fetch
		List<Object[]> blist=boardRepo.getFilesInfo2();
		blist.forEach(arr->{
			System.out.println(Arrays.toString(arr));
		});
	}
	
	
	//@Test
	public void test3() {
		//LAZY Fetch
		boardRepo.getFilesInfo(115L).forEach(arr -> {
			System.out.println(Arrays.toString(arr));
			});
		System.out.println("-----------------------");
		
		//Eager Fetch
		PDSBoard board = boardRepo.findById(115L).orElse(null);
		if(board!=null) {
			System.out.println(board.getPname());
			System.out.println(board.getPwriter());
			System.out.println(board.getFiles2());
			
		}
		}

	//연결되어있는 pdsno삭제
	//@Test
	void deleteByBoard() {
		Long bno=496L;
		boardRepo.deleteById(bno);
	}
	
	//@Test
	void deleteByFile() {
		Long[] ar= {323L, 264L, 265L, 266L};
		Arrays.stream(ar).forEach(bno->{
			fileRepo.deleteById(bno);
			
		});
		
		
	}
	
	@Test
	void selectAllBoard() {
		boardRepo.findAll().forEach(board -> {
			log.info("보드이름:"+ board.getPname() + 
					"작성자:" + board.getPwriter() + 
					"첨부파일 건수:" + board.getFiles2().size() + "건 ");
		});
	}

	//@Test
	void insertAll() {
			IntStream.range(20, 30).forEach(j -> {
			List<PDSFile> flist = new ArrayList<>();
			IntStream.range(1, 6).forEach(a -> {

				PDSFile f = PDSFile.builder().pdsfilename("firstzone-" + a + ".java").build();
				flist.add(f);
			});
			PDSBoard board = PDSBoard.builder().pname("springFramework수업--" + j).pwriter("은빈").files2(flist).build();
			boardRepo.save(board);
		});

	}
}
