package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import pack.dto.DeptDto;
import pack.dto.EmpDto;
import pack.entity.Dept;
import pack.entity.Emp;
import pack.repository.DeptRepository;
import pack.repository.EmpRepository;

@Controller
public class EmployeeController {

	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired
	private EmpRepository empRepo;
	
	@Autowired
	private DeptRepository deptRepo;
	
	@GetMapping("/employee/list")
	public String empList(Model model) {
		// 모든 직원 정보 출력
		//List<Emp> empList = empRepo.findAll(); // 기본 지원 메서드
		//List<Emp> empList = empRepo.findAllByOrderByEmpnoAsc(); // query method
		//List<Emp> empList = empRepo.findAllByOrderByEmpnoDesc(); // query method
		List<Emp> empList = empRepo.findAllList(); // JPQL
		//List<Emp> empList = empRepo.findList(3000); // JPQL 3000 이상 받는 직원
		model.addAttribute("list", empList);
		
		return "employee/elist";
	}
	
	@GetMapping("/employee/dept")
	public String deptList(Model model,
							@RequestParam("deptno") int deptno) {
		Dept dept = deptRepo.findById(deptno).get();	
		DeptDto deptDto = DeptDto.toDto(dept); // DeptDto에 count 필드 있음
		model.addAttribute("dto", deptDto);
		return "employee/dept";
	}
	
	// JPQL 연습장
	@GetMapping("/jpql")
	public String jpql() {
		
		return "jpql";
	}
	
	@ResponseBody // JSON 형태(타입 X)로 반환
	@PostMapping("/jpql/test") // Ajax가 요청한 값
	public List<EmpDto> test(@RequestParam("query") String query){ // query=select+e+from+Emp+e
		//System.out.println(query); // select e from Emp e
		
		// EntityManager 사용해서 query 수행
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<EmpDto> list = null;
		try {
			// 전달받은 query(JPQL)문을 실행
			TypedQuery<Emp> tQuery = em.createQuery(query, Emp.class);
			
			list = tQuery.getResultStream().map(EmpDto::toDto).toList(); // map() 함수를 실행하는 함수
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		return list;
	} 
}
