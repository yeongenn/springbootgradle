package com.example.springweb999_eun.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table
public class Gogek {

    @Id
    @Column(name="gogek_no")
    private String no;

    @Column(name="gogek_name")
    private String name;

    @Column(name="gogek_tel")
    private String gogekTel;

    @ManyToOne//(fetch = FetchType.EAGER) // 테이블 연관관계
    @JoinColumn(name="gogek_damsano", referencedColumnName = "jikwon_no")
    private Jikwon jikwon;
}
