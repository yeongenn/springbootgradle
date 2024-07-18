package pack;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pack.model.Jikwon;
import pack.model.JikwonCrudRepository;

@SpringBootApplication
public class Springweb14DbJpaRApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springweb14DbJpaRApplication.class, args)
		.getBean(Springweb14DbJpaRApplication.class).execute();
	}
	
	@Autowired
	JikwonCrudRepository jikwonRepository;
	
	private void execute() {
		System.out.println("240718 Spring Boot JPA 복습~");
		
		selectJikwon();
//		Scanner sc = new Scanner(System.in);
//		System.out.print("일정 연봉 초과 직원 검색 : ");
//		int pay = sc.nextInt();
		jikwonByPay(6000);
		jikwonByJik("대리");
	}
	
	private void selectJikwon() {
		System.out.println("전체 직원 자료 읽기 - 기본 지원 findAll()");
		List<Jikwon> jikwonList = jikwonRepository.findAll();
		
		for(Jikwon j : jikwonList) {
			System.out.println(j.getNo() + " " +
								j.getName() + " " +
								j.getBuser().getBuserName() + " " +
								j.getJik() + " " +
								j.getPay() + " " +
								j.getGender());
		}
	}
	
	private void jikwonByPay(int pay) {
		System.out.println("\n\n일정 연봉 초과 직원 읽기 - 쿼리메서드 findByPayGreaterThan()");
		List<Jikwon> jikwonList2 = jikwonRepository.findByPayGreaterThan(pay);
		
		for(Jikwon j : jikwonList2) {
			System.out.println(j.getNo() + " " +
								j.getName() + " " +
								j.getJik() + " " +
								j.getPay() + " " +
								j.getGender());
		}
	}
	
	private void jikwonByJik(String jik) {
		System.out.println("\n\n직급별 직원 읽기 - 임의생성 쿼리메서드 findByJik()");
		List<Jikwon> jikwonList3 = jikwonRepository.findByJik(jik);
		for(Jikwon j : jikwonList3) {
			System.out.println(j.getNo() + " " +
								j.getName() + " " +
								j.getJik());
		}
	}

}
