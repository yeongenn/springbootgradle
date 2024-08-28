package com.example.springweb999_eun.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDao{

    @Autowired
    private JikwonRepository jikwonRepository;

    @Autowired
    private GogekRepository gogekRepository;

    // 부서별 직원
    public List<Jikwon> getJikwonByBuser(String buserName){
        List<Jikwon> jikwonList = jikwonRepository.findByBuserName(buserName);

        return jikwonList;
    }

    // 평균 연봉 이상 직원
    public List<Jikwon> getJikwonOverAvgPay(){
        List<Jikwon> jikwonList = jikwonRepository.findByPayGreaterThanEqualAvg();

        return jikwonList;
    }

    // 담당 고객 한명 이상 직원
    public List<Jikwon> getJikwonChargeOfGogek(){
        List<Jikwon> jikwonList = jikwonRepository.findByCountGogek();

        return jikwonList;
    }

    // 직원별 고객
    public List<Gogek> getGogekByJikwon(String jikwonNo){
        List<Gogek> gogekList = gogekRepository.findByJikwonNo(jikwonNo);
        return gogekList;
    }
    
   
    
}
