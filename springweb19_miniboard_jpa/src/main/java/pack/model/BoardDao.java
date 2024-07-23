package pack.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pack.controller.BoardBean;

@Repository
public class BoardDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataRepository dataRepository;
	
	// 전체 자료 읽기
	public List<Board> list(){
		List<Board> list = dataRepository.findAll();
		logger.info("list size : " + list.size());
		return list;
	}
	
	// 자료 검색
	public List<Board> searchList(BoardBean bean){
		List<Board> slist = null;
		if(bean.getSearchName().equals("author")) {
			slist = dataRepository.searchLike(bean.getSearchValue());
		} else {
			slist = dataRepository.searchLike2(bean.getSearchValue());
		}
		
		return slist;
	}
	
	// 자료 추가
	/*
	프록시 객체는 해당 메서드가 처리될 때 Commit 혹은 Rollback을 수행
	@Transactional
	CheckedException 또는 예외가 없는 경우 Commit 수행
	UncheckedException이 발생하면 Rollback 수행
	 */
	@Transactional
	public String insertData(BoardBean bean) {
		try {
			// 새글 입력 시 가장 큰 번호를 얻어 +1
			int max = dataRepository.maxNum();
			
			Board dto = new Board();
			dto.setNum(max + 1);
			dto.setAuthor(bean.getAuthor());
			dto.setTitle(bean.getTitle());
			dto.setContent(bean.getContent());
			dto.setBwrite(Timestamp.valueOf(LocalDateTime.now()));
			dto.setReadcnt(0);
			
			dataRepository.save(dto);
			
			return "success";
		} catch (Exception e) {
			return "입력 오류 : " + e.getMessage();
		}
	
	}
	
	// 상세 보기, 조회 수 증가
	// 조회 수 증가하면서 update 사용하므로 트랜잭션 걸어주기
	@Transactional
	public Board detail(int num) {
		// 조회 수 증가
		dataRepository.updateReadcnt(num);
		
		/*
		 Optional
		 NPE 방지가 목적
		 Spring Data JPA를 사용할 때 Repository에서 findById()의 반환 타입
		 */
		
		Optional<Board> board = dataRepository.findById(num);
		logger.info("board : {}", board.get());
		
		// isEmpty, isPresent
		if(board.isEmpty()) {
			return new Board();
		} else {
			return board.get();
		}

		
	}
	
	// 게시글 수정
	@Transactional
	public String updateData(BoardBean bean) {
		try {
			
			
			// 조회수도 수정에 참여하기 위한 선행 작업
			Optional<Board> board = dataRepository.findById(bean.getNum());
			Board imsi = board.get();
			
			/*
			Board dto = new Board();
			dto.setNum(bean.getNum()); 
			dto.setAuthor(bean.getAuthor());
			dto.setTitle(bean.getTitle());
			dto.setContent(bean.getContent());
			dto.setReadcnt(imsi.getReadcnt());
			
			// 이미 등록된 num 이므로 수정(update)
			dataRepository.save(dto);
			*/
			
			// save() 없는 버전
			imsi.setAuthor(bean.getAuthor());
			imsi.setTitle(bean.getTitle());
			imsi.setContent(bean.getContent());
			imsi.setReadcnt(imsi.getReadcnt());
			imsi.setBwrite(imsi.getBwrite());
			
			return "success";
		} catch (Exception e) {
			return "수정 오류 : " + e.getMessage();
		}
	}
	
	// 게시글 삭제
	@Transactional
	public String deleteData(int num) {
		try {
			dataRepository.deleteById(num);
			return "success";
		} catch (Exception e) {
			return "수정 오류 : " + e.getMessage();
		}
	}
	
	
}
