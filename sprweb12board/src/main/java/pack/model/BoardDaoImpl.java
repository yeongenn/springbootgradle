package pack.model;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pack.controller.BoardBean;

@Repository
public class BoardDaoImpl {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DataRepository repository;

	public Page<Board> listAll(int page) {  // 전체 자료 읽기 (내림차순 정렬)
		//List<Board> list = repository.findAll(Sort.by(Sort.Direction.DESC, "gnum", "onum")); // 둘다 내림차순
		
		// 다중 sort인데 다른 order를 주려면 어떻게 해야할까?
		// List<Board> list = repository.findAll(Sort.by(Sort.Order.desc("gnum"), Sort.Order.desc("onum")));
		Sort sort = Sort.by(Sort.Order.desc("gnum"), Sort.Order.asc("onum"));
	    Pageable paging = PageRequest.of(page, 10, sort);
	    Page<Board> list = repository.findAll(paging);
		
		// 전체 레코드 수가 12개라고 한다면
		System.out.println("page number : " + list.getPageable().getPageNumber() + 1); //JPA에서 페이지는 '0'부터 시작
		System.out.println("page size : " + list.getSize());   // 10
		System.out.println("total pages : " + list.getTotalPages());  // 2
		System.out.println("total count : " + list.getTotalElements());  // 12
		System.out.println("next : " + list.nextPageable());  //Page request [number:1, size 10, sort:gnum:DESC,onum:ASC]
		
		logger.info("반환된 레코드 수 : {}", list.getContent().size());  // getContent()로 List<Board> 꺼내기
		return list; 
	}
	
	public Page<Board> search(BoardBean bean){   // 검색용
		System.out.println("search : " + bean.getSearchName() + " " + bean.getSearchValue());

		Sort sort = Sort.by(Sort.Order.desc("gnum"), Sort.Order.asc("onum"));
		Pageable paging = PageRequest.of(0, 10, sort);
	    Page<Board> slist;

		if(bean.getSearchName().equals("name")){
			slist = repository.searchLike(paging, bean.getSearchValue());
		}else {
			slist = repository.searchLike2(paging, bean.getSearchValue());
		}
		return slist;
	}

	public int currentMaxNum() { // 추가 시 num 자동증가를 위해 레코드 중 가장 큰 번호 얻기
		return repository.maxNum();
	}
	
	// @Transactional : try ~ catch 문을 적용하려면 @Transactional을 사용하지 않는다.
	public String insert(BoardBean bean) {
		try {
			Board dto = new Board();
			dto.setNum(bean.getNum());
			dto.setName(bean.getName());
			dto.setPass(bean.getPass());
			dto.setMail(bean.getMail());
			dto.setTitle(bean.getTitle());
			dto.setCont(bean.getCont());
			dto.setBip(bean.getBip());
			dto.setBdate(bean.getBdate());
			dto.setReadcnt(bean.getReadcnt());
			dto.setGnum(bean.getGnum());
			dto.setOnum(bean.getOnum());
			dto.setNested(bean.getNested());
			dto = repository.save(dto);  // insert 처리
			System.out.println("insert memDto : " + dto);
			return "success";
		} catch (Exception e2) {
			return "입력자료 오류 ~ : " + e2.getMessage();
		}
	}

	@Transactional
	public void updateReadcnt(int num) {  // 상세보기 전에 조회 수 증가
		repository.updateReadcnt(num);
	}

	public Board detail(int num) {  // 상세 보기, 글수정, 댓글에서 num에 해당되는 한 개의 레코드 읽기
		// Spring Data JPA 사용 시 Repository에서는 리턴 타입을 Optional로 바로 받을 수 있도록 지원.
        // Optional을 사용하면 반복적인 null 체크를 줄일 수 있기 때문에 잘 사용하면 편리.
		Optional<Board> board =  repository.findById(num);
		logger.info("board :: {}", board.get());
		
		if(board.isPresent()) {
			return board.get();   // Optional ==> Board 타입으로 반환 
        }else {
        	return new Board();
        }
	}

	public String selectPass(int num) { // 수정시 비밀번호 비교용
		return repository.selectPass(num);
	}
	
	@Transactional  // Update, Delete의 경우 Transaction 처리가 필요
	public String update(BoardBean bean) {
		try {
			Optional<Board> board = repository.findById(bean.getNum());
			Board dto = board.get();
			dto.setName(bean.getName());
			dto.setMail(bean.getMail());
			dto.setTitle(bean.getTitle());
			dto.setCont(bean.getCont()); 
			//save()를 사용하지 않아도 수정이 된다.(객체가 자기 할 일만 하는 코드 - 깔끔!)
			System.out.println("update memDto : " + dto);
			return "success";
		} catch (Exception e2) {
			return "수정자료 오류 ~ : " + e2.getMessage();
		}
	}

	@Transactional  // Update, Delete의 경우 Transaction 처리가 필요
	public String delete(int num) {
		try {
			repository.deleteById(num);
			return "success";
		} catch (Exception e2) {
			return "삭제처리 오류 ~ : " + e2.getMessage();
		}
	}

	// 댓글 처리 -------------------------------------------------
	@Transactional  
	public void updateOnum(BoardBean bean) { // 댓글에서 onum 갱신
		System.out.println("2--------" + bean.getGnum() + " " + bean.getOnum());
		try {
			repository.updateOnum(bean.getGnum(), bean.getOnum());
			System.out.println("updateOnum err");
		} catch (Exception e2) {
			System.out.println("updateOnum err : " + e2.getMessage());
		}
	}
	
	public String insertReply(BoardBean bean) {
		try {
			Board dto = new Board();
			dto.setNum(bean.getNum());
			dto.setName(bean.getName());
			dto.setPass(bean.getPass());
			dto.setMail(bean.getMail());
			dto.setTitle(bean.getTitle());
			dto.setCont(bean.getCont());
			dto.setBip(bean.getBip());
			dto.setBdate(bean.getBdate());
			dto.setReadcnt(0);
			dto.setGnum(bean.getGnum());
			dto.setOnum(bean.getOnum());
			dto.setNested(bean.getNested());
			dto = repository.save(dto);  // insert reply 처리
			System.out.println("insert reply memDto : " + dto);
			return "success";
		} catch (Exception e2) {
			return "댓글 입력자료 오류 ~ : " + e2.getMessage();
		}
	}
}
