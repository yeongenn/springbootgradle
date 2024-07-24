package com.example.springweb999_jpa_jpql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springweb999_jpa_jpql.entity.Jikwon;
import com.example.springweb999_jpa_jpql.repository.BuserRepository;
import com.example.springweb999_jpa_jpql.repository.JikwonRepository;

@SpringBootApplication
public class Springweb999JpaJpqlApplication {

	
	@Autowired
	private JikwonRepository jikwonRepository;
	
	@Autowired
	private BuserRepository buserRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Springweb999JpaJpqlApplication.class, args)
		.getBean(Springweb999JpaJpqlApplication.class).execute();
	}
	
	public void execute() {
		// 부서별 직원 - from buser
		getJikwonByBuser(20);
		
		// 부서별 직원 - from jikwon		
		
	}
	
	public void getJikwonByBuser(int num) {
		List<Jikwon> jikwonList = jikwonRepository.findByBuserNum(num);
		for(Jikwon j : jikwonList) {
			System.out.println(j.getNo() + "\t" +
								j.getName() + "\t" +
								j.getJik() + "\t" +
								j.getBuser().getBname() + "\t" +
								j.getPay());
		}
	}

}
