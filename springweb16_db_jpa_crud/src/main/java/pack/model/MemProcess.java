package pack.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.MemBean;

@Repository
public class MemProcess {

	@Autowired
	private MemRepository repository;
	
	// 전체 자료 읽기
	public List<Mem> getDataAll(){
		List<Mem> mlist = repository.findAll();
		return mlist;
	}
	
	// 자료 추가
	public String insert(MemBean bean) {
		// pk - auto_increment
		// int max = repository.findByMaxNum();
		// ...
		
		// pk - duplicate check
		try {
			// 해당번호 mem 이미 존재
			Mem mem = (Mem) repository.findById(bean.getNum()).get();
//			System.out.println("mem : " + mem);
			return "이미 입력된 번호입니당";
		} catch (Exception e) {
			try {
				// 만약 없다면 추가
				Mem mem = new Mem();
				mem.setNum(bean.getNum());
				mem.setName(bean.getName());
				mem.setAddr(bean.getAddr());
				mem = repository.save(mem);
				return "성공";
			} catch (Exception e2) {
				return "입력 오류 " + e2.getMessage();
			}
		}
		
	}
	// 자료 1개 읽어오기(수정, 삭제용)
	public Mem getData(String num) {
		Mem mem = repository.findByNum(num);
		return mem;
	}
	 
	// 자료 수정
	public String update(MemBean bean) {
		
		try {
			Mem mem = new Mem();
			mem.setNum(bean.getNum());
			mem.setName(bean.getName());
			mem.setAddr(bean.getAddr());
			repository.save(mem);
			return "성공";
		} catch (Exception e2) {
			return "수정 오류 " + e2.getMessage();
		}
		
	}
	
	
	// 자료 삭제
	public String delete(int num) {
			
		try {
			repository.deleteById(num);
			return "성공";
		} catch (Exception e2) {
			return "삭제 오류 " + e2.getMessage();
		}
			
	}
}

