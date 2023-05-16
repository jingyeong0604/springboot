package com.shinhan.education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Commit;

import com.shinhan.education.repository.BoardRepository;
import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.repository.PDSFileRepository;
import com.shinhan.education.vo.PDSBoard;
import com.shinhan.education.vo.PDSFile;

@SpringBootTest
@Commit // 실행은 성공하지만 test 환경이므로 rollback처리가 됨->원래대로 돌아감. (변경되지가 않음)
//Commit 사용하자~!
public class OneToManyTest2 {
	@Autowired
	PDSFileRepository fileRepo;

	@Autowired
	PDSBoardRepository boardRepo;
	//질문 안됨
	@Test //일대다 insert가 안됨.
	void boardFileInsert() {
		boardRepo.findById(4L).ifPresent(board -> {
			List<PDSFile> files2 = board.getFiles2();
			PDSFile file1 = PDSFile.builder().pdsfilename("추가1").build();
			PDSFile file2 = PDSFile.builder().pdsfilename("추가1").build();
			boardRepo.save(board);
			//board를 통해서 파일을 2건 추가함.
		});
	}

	// Board 이용해서 FILE 이름 수정하기
	//@Test
	void boardFileUpdate() {
		boardRepo.findById(4L).ifPresent(board -> {
			List<PDSFile> files2 = board.getFiles2();
			files2.forEach(f -> {
				f.setPdsfilename("수정2");
				fileRepo.save(f);

			});
		});
	}

	// PDSBoardRepository(부모를)를 이용해서 자식 수정하기
	@Transactional // @Modifying을 사용한 경우 반드시 @Transactional사용->repository에서 사용하자~
	// @Transacitonal: rollback처리한다!
	@Test
	// sol) class level에 커밋 추가하기!
	void fileUpdate() {
		boardRepo.updateFile(5L, "풍경사진");
	}

	////// 1대 n
	// @Transactional//lazy를 사용해도 자식을 가져오고 싶은 경우에 사용함
	// 즉 LAZY인 경우..자식에 접근가능
	// board에서 file조회(자식 조회)
	// @Test
	void test7() {
		boardRepo.findAll().forEach(b -> {
			System.out.println(b);
			System.out.println(b.getFiles2());
		});
	}

	// 1에서 n을 넣기->부모에서 자식을 insert하자
	// @Test
	void test6() {
		List<PDSFile> files = new ArrayList<>();
		PDSFile file = PDSFile.builder().pdsfilename("얼굴사진1").build();
		PDSFile file2 = PDSFile.builder().pdsfilename("얼굴사진2").build();
		PDSFile file3 = PDSFile.builder().pdsfilename("얼굴사진3").build();
		files.add(file);
		files.add(file2);
		files.add(file3);
		// board에 file 3건을 insert한다.
		PDSBoard board = PDSBoard.builder().pname("월요일입니다악").pwriter("진경").files2(files).build();
		boardRepo.save(board);
	}

////////
	// 부모 조회시 ->자식도 조회됨 CascadeType.ALL이 아니라면 부모를 통해서 자식을 넣을 수 없음
	@Test
	void test5() {

		// 파일 가져와서 저장
		PDSFile file = fileRepo.findById(1L).orElse(null);
		if (file != null) {
			// PDSBoard board = boardRepo.findById(2L).orElse(null);// boardRepo에서 2번을 가져옴
			// file에 바로 넣을 수 없음 ->쿼리문장 이용 칼럼은 있으나 자바에서는 칼럼이 없기 떄문에 사용 불가

			file.setPdsfilename("파일이름수정");
			fileRepo.save(file);
		}
	}

	// @Test //부모만 조회 ->자식은 null값이 나옴
	void test4() {
		boardRepo.findAll().forEach(b -> System.out.println(b));
	}

	// 부모만 insert 한 것
	// @Test //pdsno는 없는채로 저장 (자식은 없는 채로 저장)
	void test3() {
		PDSBoard board = PDSBoard.builder().pname("게시글").pwriter("작성자").build();
		boardRepo.save(board);
	}

	// @Test pdsno null값이 올 수 있음
	void test2() {
		fileRepo.findAll().forEach(f -> System.out.println(f));
	}

	// @Test pdsno은 null //자식만 insert 한 것
	void test1() {
		PDSFile file = PDSFile.builder().pdsfilename("첨부파일1").build();
		fileRepo.save(file);
	}
}
