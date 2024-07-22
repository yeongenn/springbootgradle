package pack.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import pack.controller.BoardBean;

@Repository
public class BoardDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataMapInterface dataMapInterface;

	// 전체 리스트
	public List<Board> list(){
		List<Board> list = null;
		try {
			list = dataMapInterface.selectList();
		} catch (Exception e) {
			logger.info("list err : " + e);
		}
		return list;
	}
	
	// 글 추가
	public boolean insertData(BoardBean bean) {
		boolean b = false;
		int re = dataMapInterface.insert(bean);
		if(re > 0) {
			b = true;
		}
		return b;
	}
	
	// 검색 결과 리스트
	public List<Board> searchList(BoardBean bean){
		List<Board> searchList = null;
		try {
			searchList = dataMapInterface.selectSearch(bean);
		} catch (Exception e) {
			logger.info("searchList err : " + e);
		}
		return searchList;
	}
	
	// (조회 수 증가 후)상세보기
	public Board detail(String num) {
		dataMapInterface.updateReadcnt(num);
		
		Board board = dataMapInterface.selectOne(num);
		return board;
	}
	
	// 수정하기
	public boolean updateData(BoardBean bean) {
		boolean b = false;
		int re = dataMapInterface.update(bean);
		if(re > 0) {
			b = true;
		}
		return b;
	}
	
	// 삭제하기
	public boolean deleteData(BoardBean bean) {
		boolean b = false;
		int re = dataMapInterface.delete(Integer.toString(bean.getNum()));
		if(re > 0) {
			b = true;
		}
		return b;
	}

}
