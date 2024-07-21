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

    @OneToMany(mappedBy = "jikwon")
    private List<Gogek> gogekList;
}
