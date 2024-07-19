package pack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pack.model.ProductCrudRepository;
import pack.model.ProductVo;

@SpringBootApplication
public class Springweb14DbJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springweb14DbJpaApplication.class, args)
		.getBean(Springweb14DbJpaApplication.class).execute();
	}
	
	@Autowired
	ProductCrudRepository repository;
	
	private void execute() {
		System.out.println("execute");
		
		insertData();
		selectData();
	}
	
	private void insertData() {
		/*
		ProductVo productVo = new ProductVo();
		productVo.setSang("볼펜");
		productVo.setSu(3);
		productVo.setDan(3000);
		productVo = repository.save(productVo);
		System.out.println("등록 데이터 : " + productVo);
		*/
		
		ProductVo productVo = new ProductVo();
		productVo.setCode(1);
		productVo.setSang("지우개");
		productVo.setSu(1);
		productVo.setDan(2500);
		productVo = repository.save(productVo);
		
	}
	
	private void selectData() {
		System.out.println("전체 자료 읽기 : DBMS에 독립적이다");
		List<ProductVo> list = repository.findAll();
		
		for(ProductVo vo : list) {
			System.out.println(vo.getCode() + " " + 
								vo.getSang() + " " + 
								vo.getSu() + " " +
								vo.getDan());
		}
		
		System.out.println("\n부분 자료 읽기");
		ProductVo vo = repository.findById(1).get();
		System.out.println(vo.getCode() + " " + 
							vo.getSang() + " " + 
							vo.getSu() + " " +
							vo.getDan());
		
		System.out.println("\n부분 자료 읽기(JPA 쿼리 메서드 사용)");
		ProductVo vo3 = repository.findByCode(3);
		System.out.println(vo3.getCode() + " " + 
				vo3.getSang() + " " + 
				vo3.getSu() + " " +
				vo3.getDan());
		
		System.out.println("\n---JPQL 사용---");
		List<ProductVo> list2 = repository.findAllData(); // 임의로 만든 메서드
		for(ProductVo vo2 : list2) {
			System.out.println(vo2.getCode() + " " + 
								vo2.getSang() + " " + 
								vo2.getSu() + " " +
								vo2.getDan());
		}
		
		System.out.println("\n부분 자료 읽기(JPA 쿼리 메서드 사용, where절)");
		ProductVo vo4 = repository.findByCodeMy(4); // 임의 생성 메서드(이름 기반)
		System.out.println(vo4.getCode() + " " + 
				vo4.getSang() + " " + 
				vo4.getSu() + " " +
				vo4.getDan());
		
		ProductVo vo5 = repository.findByCodeMy2(4); // 임의 생성 메서드(위치 기반)
		System.out.println(vo5.getCode() + " " + 
				vo5.getSang() + " " + 
				vo5.getSu() + " " +
				vo5.getDan());
		
		List<ProductVo> list3 = repository.findByData(5, "파이"); // 임의로 만든 메서드
		for(ProductVo vo6 : list3) {
			System.out.println(vo6.getCode() + " " + 
								vo6.getSang() + " " + 
								vo6.getSu() + " " +
								vo6.getDan());
		}
		
		System.out.println("\n\n전체 자료 읽기 : Native Query"); // Native Query 사용
		List<ProductVo> list4 = repository.findAllData2();		
		for(ProductVo vo7 : list4) {
			System.out.println(vo7.getCode() + " " + 
								vo7.getSang() + " " + 
								vo7.getSu() + " " +
								vo7.getDan());
		}
		
		
	}

}
