package com.example.springweb999_eun.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
@Table
public class Jikwon {

    @Id
    @Column(name="jikwon_no")
    private String no;

    @Column(name="jikwon_name")
    private String name;

    @Column(name="jikwon_jik")
    private String jik;

    @Column(name="jikwon_pay")
    private int pay;

    @ManyToOne//(fetch = FetchType.EAGER) // 테이블 연관관계
    @JoinColumn(name="buser_num", referencedColumnName = "buser_no")
    private Buser buser;
    
    /*
     @JoinColumn
     name : 대상 엔티티와 매핑할 외래키(자기 필드명) 이름을 지정
     referencedColumnName : 조인할 칼럼명(외래키가 참조하는 대상 테이블의 칼럼명)을 지정
     
     근데 referencedColumnName는 적어주지 않아도 알아서 맵핑이 되는데?
     referencedColumnName의 default 값이 참조하는 대상 테이블의 기본키의 변수명으로 알아서 조인하도록 동작되거든
     */

    @OneToMany(mappedBy = "jikwon")
    private List<Gogek> gogekList;
}
