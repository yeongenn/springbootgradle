package com.example.demo;

import com.example.demo.model.MatchRepository;
import com.example.demo.model.MatchResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
//		SpringApplication.run(DemoApplication.class, args)
//				.getBean(DemoApplication.class).execute();

	}

	@Autowired
	private MatchRepository matchRepository;

	public void execute(){
		getResultByYear("ULS", "ULS", "2024");
		getResultByGubun("ULS", "2024");
	}

	public void getResultByYear(String hometeam, String awayteam, String year){
		List<MatchResultDto> list = matchRepository.matchResultByYear(hometeam, awayteam, year);
		for(MatchResultDto dto : list){
			System.out.println(dto.getWhole_game() + "\t" +
								Math.round(dto.getPoss_percent()*100)/100.0 + "\t" +
								Math.round(dto.getSot_percent()*100)/100.0 + "\t" +
								Math.round(dto.getTac_percent()*100)/100.0);
		}

	}
	
	public void getResultByGubun(String teamcode, String year) {
		List<MatchResultDto> list = matchRepository.matchResultByGubun(teamcode, year);
		for(MatchResultDto dto : list){
			System.out.println(dto.getWhole_game() + "\t" +
								Math.round(dto.getPoss_percent()*100)/100.0 + "\t" +
								Math.round(dto.getSot_percent()*100)/100.0 + "\t" +
								Math.round(dto.getTac_percent()*100)/100.0);
		}
	}

}
