package com.example.springweb999_jpa_jpql;

import java.util.ArrayList;
import java.util.List;

import com.example.springweb999_jpa_jpql.repository.JikwonDao;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springweb999_jpa_jpql.entity.Buser;
import com.example.springweb999_jpa_jpql.entity.Jikwon;
import com.example.springweb999_jpa_jpql.repository.BuserRepository;
import com.example.springweb999_jpa_jpql.repository.JikwonRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@SpringBootApplication
public class Springweb999JpaJpqlApplication {

	// 확인용 코드 전체 과정 참고 사이트
	//https://gilssang97.tistory.com/45
	
	// 240724 N:1, FetchType=LAZY로 실습
	
	// 240725 proxy - checkProxy()
	
	@Autowired
	private JikwonRepository jikwonRepository;
	
	@Autowired
	private BuserRepository buserRepository;

	@Autowired
	private JikwonDao jikwonDao;
	
	@Autowired
	private EntityManagerFactory emf;

	
	
	

	public static void main(String[] args) {
		SpringApplication.run(Springweb999JpaJpqlApplication.class, args)
		.getBean(Springweb999JpaJpqlApplication.class).execute();
	}
	
	public void execute() {
		// 부서별 직원 - from buser <- 1:N
		// 1. join X


		// 2. 일반 join

		// 3. fetch join
		// 어노테이션에 명시하는 거랑 JPQL 쓰는 거랑 별개인지...?
		// 3-1. FetchType=LAZY

		// 3-2. FetchType=EAGER
		
		// 부서별 직원 - from jikwon <- N:1
		// 1. join X
		//getJikwonByBuser("전산부");

		// 2. 일반 join
		//getJikwonByBuserJoin("전산부");

		// 3. fetch join
		//getJikwonByBuserFetchJoin("전산부");
		
		// --------------------------------
		checkProxy();
	}
	
	
	
	
	// join X
	public void getJikwonByBuser(String bname) {
		List<Jikwon> jikwonList = jikwonRepository.findByBuserBname(bname);
		for(Jikwon j : jikwonList) {
			System.out.println(j.getNo() + "\t" +
								j.getName() + "\t" +
								j.getJik() + "\t" +
								j.getBuser().getBname() + "\t" +
								j.getPay());
		}
	}

	
	// 일반 join
	public void getJikwonByBuserJoin(String bname){
		/*
		ArrayList<Jikwon> jikwonList = (ArrayList<Jikwon>) jikwonRepository.findByBuserBnoJoin(bname);
		System.out.println(jikwonList.get(0).getBuser().getClass().getName());
		// com.example.springweb999_jpa_jpql.entity.Buser
		// @OneToMany에 패치 달아주면 에러X, 근데 프록시가 아니라 원본 객체가 찍혀나옴
		// @ManyToOne에 달아주니까 에러난다
		// 왜 일반 조인했는데 원본 객체가 나오지...?
		// https://yjksw.github.io/jpa-lazyinitialization-exception/
		// jikwon 스캔 한번하는데 이때 buser 엔티티에서는 필요한 필드만 스캔
		// 그리고 buser 한번 더 스캔 -> N+1
		for(Jikwon j : jikwonList) {
			System.out.println(j.getNo() + "\t" +
					j.getName() + "\t" +
					j.getJik() + "\t" +
					j.getBuser().getBname() + "\t" +
					j.getPay());
		}
		*/

		ArrayList<Jikwon> jikwonList = (ArrayList<Jikwon>) jikwonDao.getJikwonByBuser(bname);
		/*
		dao 클래스 따로 만들어서
		@Transactional 달아주고
		Hibernate.initialize(Object proxy) 통해서 영속성 컨텍스트 초기화 시켜준 다음에 실행하니까 프록시객체 나온다!
		com.example.springweb999_jpa_jpql.entity.Buser$HibernateProxy$YqKkCrPZ

		위에 참고 사이트 + 아래 사이트 다시 한번 읽어보기
		https://jiwondev.tistory.com/230
		 */
		for(Jikwon j : jikwonList) {
//			System.out.println(j.getBuser().getClass().getName()); // 전부 동일한 hibernate proxy가 찍혀 나온다
			System.out.println(j.getNo() + "\t" +
								j.getName() + "\t" +
								j.getJik() + "\t" +
								j.getPay() + "\t" +
								j.getBuser().getBname());
		}
	}

	
	
	// fetch join
	public void getJikwonByBuserFetchJoin(String bname){
		/*
		ArrayList<Jikwon> jikwonList = (ArrayList<Jikwon>) jikwonRepository.findByBuserBnoFetchJoin(bname);
		System.out.println(jikwonList.get(0).getBuser().getClass().getName());
		// LAZY로 설정했는데 왜 원본객체가 오는 것인지...???
		// dao 클래스 따로 만들어서 해야하나..?
		// fetch join하면 jikwon과 buser 한번에 스캔한다!
		for(Jikwon j : jikwonList) {
			System.out.println(j.getNo() + "\t" +
					j.getName() + "\t" +
					j.getJik() + "\t" +
					j.getBuser().getBname() + "\t" +
					j.getPay());
		}

		 */

		ArrayList<Jikwon> jikwonList = (ArrayList<Jikwon>) jikwonDao.getJikwonByBuserFetchJoin(bname);
		System.out.println(jikwonList.get(0).getBuser().getClass().getName());
		//com.example.springweb999_jpa_jpql.entity.Buser
		// 한번의 쿼리를 통해서 buser 객체 자체를 스캔
		// 그럼 그 buser 객체는 원본 객체일까? YES
		// 그렇다면 한번의 실행으로 원본 객체를 가져왔으니 buser 테이블을 스캔할 이유가 X -> N+1이 발생하지 X
		for(Jikwon j : jikwonList) {
			System.out.println(j.getNo() + "\t" +
					j.getName() + "\t" +
					j.getJik() + "\t" +
					j.getBuser().getBname() + "\t" +
					j.getPay());
		}

	}
	
	
	public void checkProxy() {
		//https://kha0213.github.io/jpa/jpa-proxy/
		
		EntityManager em = emf.createEntityManager();
		
		Jikwon jikwon = jikwonDao.getJikwonByNo(10);
		Buser buser = new Buser();
		buser = jikwon.getBuser();
		
		Jikwon jikwon2 = em.find(Jikwon.class, jikwon.getNo());
		
//		System.out.println("jikwon2 : " + jikwon2.getClass().getName());
//		System.out.println("jikwon2.getBuser() : " + jikwon2.getBuser().getClass().getName());

		final Buser proxyBuser = jikwon2.getBuser(); // proxy
		final Buser findBuser = em.find(Buser.class, buser.getBno()); // 이 코드가 jikwon2 전에 선언되면 아래 코드로 프록시객체가 아닌 원본 객체가 출력된다
		
		System.out.println("proxyBuser : " + proxyBuser.getClass().getName()); // Buser$HibernateProxy$bvhjkz27
		System.out.println("findBuser : " + findBuser.getClass().getName()); // Buser$HibernateProxy$bvhjkz27
		System.out.println("proxyBuser == findBuser : " + (findBuser == proxyBuser)); // true
		
	}
	 

}
