package com.example.springweb999_jpa_jpql.repository;

import com.example.springweb999_jpa_jpql.entity.Jikwon;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JikwonDao{

    @Autowired
    private JikwonRepository jikwonRepository;
    
    @Autowired
    private BuserRepository buserRepository;

    // 일반 join
    @Transactional(readOnly = true)
    public List<Jikwon> getJikwonByBuser(String bname){
        List<Jikwon> jList = jikwonRepository.findByBuserBnoJoin(bname);
        Hibernate.initialize(jList.get(1).getBuser()); // 인덱스 번호는 상관 X
        return jList;
    }

    // fetch join
    @Transactional(readOnly = true)
    public List<Jikwon> getJikwonByBuserFetchJoin(String bname){
        List<Jikwon> jList = jikwonRepository.findByBuserBnoFetchJoin(bname);
        Hibernate.initialize(jList.get(0).getBuser());
        return jList;
    }
    
    // proxy 확인용
    @Transactional
    public Jikwon getJikwonByNo(int no) {
    	Jikwon jikwon = jikwonRepository.findByNo(no);
    	Hibernate.initialize(jikwon);
    	return jikwon;
    }


}
