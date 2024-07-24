package com.example.springweb999_jpa_jpql.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Buser {

	@Id
	@Column(name="buser_no")
	private int bno;
	
	@Column(name="buser_name")
	private String bname;
	
	@Column(name="buser_loc")
	private String loc;
	
	@Column(name="buser_tel")
	private String btel;
	
	// from buser 할 때 사용하겠네
//	@OneToMany(mappedBy = "buser")
//	private List<Jikwon> jList;
}
