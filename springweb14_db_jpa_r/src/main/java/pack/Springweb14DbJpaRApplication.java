package pack;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

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
		jikwonByBuser("총무부");
	}
	
	private void selectJikwon() {
		//System.out.println("전체 직원 자료 읽기 - 기본 지원 findAll()");
		System.out.println("전체 직원 자료 읽기 - 기본 지원 findAll() Override");
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
	
	private void jikwonByBuser(String buserName) {
		System.out.println("\n\n부서별 직원 읽기 - 임의생성 쿼리메서드 findByBuserBuserName()");
		List<Jikwon> jikwonList3 = jikwonRepository.findByBuserBuserName(buserName);
		for(Jikwon j : jikwonList3) {
			System.out.println(j.getNo() + " " +
					j.getName() + " " +
					j.getBuser().getBuserName() + " " +
					j.getJik() + " " +
					j.getPay() + " " +
					j.getGender());
		}

		/*
		읽어오긴 했다!
		근데 join문으로 한번에 가져오는게 아니더라
		join문 포함된 쿼리로 한번, join 대상 테이블 한번
		너무 비효율적이지 않나?
		fetch join 이라는게 있던데...
		fetch join 쓰고 안 쓰고 차이는?
		 */

	}

}
