package com.example.springweb999_eun.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
@Table
public class Buser {

    @Id
    @Column(name="buser_no")
    private String no;

    @Column(name="buser_name")
    private String name;

    @Column(name="buser_tel")
    private String buserTel;

    @OneToMany(mappedBy = "buser")
    private List<Jikwon> jikwonList;
}
