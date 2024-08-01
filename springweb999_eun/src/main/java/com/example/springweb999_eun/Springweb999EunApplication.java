package com.example.springweb999_eun;

import com.example.springweb999_eun.model.Gogek;
import com.example.springweb999_eun.model.GogekRepository;
import com.example.springweb999_eun.model.Jikwon;
import com.example.springweb999_eun.model.JikwonRepository;
import com.example.springweb999_eun.model.TestDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Springweb999EunApplication {

	public static void main(String[] args) {

		/*
		SpringApplication.run(Springweb999EunApplication.class, args)
				.getBean(Springweb999EunApplication.class).execute();

		 */

		SpringApplication.run(Springweb999EunApplication.class, args);
	}

	/*
	- 직원 전체보기, 고객 전체보기
	- 부서별 직원 보기
	- 직원 이름 클릭하면 담당 고객 출력
	- 평균 연봉 이상 직원 보기
	- 담당 고객이 한 명이라도 있는 직원 출력
	 */

	@Autowired
	private JikwonRepository jikwonRepository;

	@Autowired
	private GogekRepository gogekRepository;
	
	@Autowired
	private TestDao testDao;

	private void execute(){
		
		getJikwonAll(); // 직원 전체 보기
		getJikwonByBuser("영업부"); // 부서별 직원 보기
		getGogekByJikwon("5"); // 직원별 담당 고객 보기
		getJikwonOverAvgPay(); // 평균 연봉 이상 직원 보기
		getJikwonOnGogek(); // 담당 고객 하나라도 있는 직원 보기
		
	}

	private void getJikwonAll(){
		List<Jikwon> jList = jikwonRepository.findAll();
		for(Jikwon j : jList){
			System.out.println(j.getNo() + "\t" +
								j.getName() + "\t" +
								j.getJik() + "\t" +
								j.getBuser().getName() + "\t" +
								j.getPay());
		}
	}

	private void getJikwonByBuser(String buserName){
		//List<Jikwon> jList = jikwonRepository.findByBuserName(buserName); // jpaRepository 
		List<Jikwon> jList = testDao.getJikwonByBuser(buserName); // dao (has a - jpaRepository)
		for(Jikwon j : jList){
			System.out.println(j.getNo() + "\t" +
								j.getName() + "\t" +
								j.getJik() + "\t" +
								j.getBuser().getName() + "\t" +
								j.getBuser().getBuserTel() + "\t" +
								j.getPay());
		}
	}

	private void getGogekByJikwon(String jikwonNo){
		List<Gogek> gList = gogekRepository.findByJikwonNo(jikwonNo);
		for(Gogek g : gList){
			System.out.println(g.getNo() + "\t" +
								g.getName() + "\t" +
								g.getJikwon().getName() + "\t" +
								g.getGogekTel());
		}
	}

	private void getJikwonOverAvgPay(){
		List<Jikwon> jList = jikwonRepository.findByPayGreaterThanEqualAvg();
		System.out.println("평균 연봉 이상 직원 ---------------");
		for(Jikwon j : jList){
			System.out.println(j.getNo() + "\t" +
								j.getName() + "\t" +
								j.getJik() + "\t" +
								j.getBuser().getName() + "\t" +
								j.getPay());
		}

	}

	private void getJikwonOnGogek(){
		List<Jikwon> jList = jikwonRepository.findByCountGogek();
		System.out.println("담당 고객이 한 명 이상인 직원 ------------");
		for(Jikwon j : jList){
			System.out.println(j.getNo() + "\t" +
								j.getName() + "\t" +
								j.getJik() + "\t" +
								j.getBuser().getName() + "\t" +
								j.getPay());
		}
	}



}
