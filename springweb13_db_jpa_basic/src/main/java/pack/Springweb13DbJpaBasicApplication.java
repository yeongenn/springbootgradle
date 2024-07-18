package pack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import other.OtherClass;
import pack.other.ProductCrudRepository;
import pack.other.ProductVo;

@SpringBootApplication
@ComponentScan(basePackages = {"other"})
public class Springweb13DbJpaBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springweb13DbJpaBasicApplication.class, args)
				.getBean(Springweb13DbJpaBasicApplication.class).myExecute();
	}
	
	@Autowired
	ProductCrudRepository crudRepository;
	
	@Autowired
	OtherClass otherClass1;
	
	private void myExecute() {
		System.out.println("웹으로 가지 않고 독립적인 프로그램으로 실행 가능");
		
		insData();
		delData();
		selectData();
		
		otherClass1.abc();
		
	}
	
	private void insData() {
		
		/*
		ProductVo productVo = new ProductVo(null, "볼펜", 2, 1000); // code는 auto_increment
		crudRepository.save(productVo); // save() 역할 (1) - insert
		*/
		
		ProductVo productVo = new ProductVo(2, "사랑비", 2, 2000); // 있는 번호에 값을 주면 update
		crudRepository.save(productVo); // save() 역할 (2) - update
	}
	
	private void delData() {
		crudRepository.deleteById(3);
	}
	
	private void selectData() {
		// findAll() - select * 문이 내부적으로 실행된다
		List<ProductVo> list = (List<ProductVo>) crudRepository.findAll();
		for(ProductVo p : list) {
			System.out.println(p.getCode() + " " + 
								p.getSang() + " " + 
								p.getSu() + " " + 
								p.getDan());
		}
		
		System.out.println();
		
		// 1개 레코드 읽기
		ProductVo vo = crudRepository.findById(2).get();
		System.out.println(vo.getCode() + " " + 
							vo.getSang() + " " + 
							vo.getSu() + " " + 
							vo.getDan());
	}
	

}
