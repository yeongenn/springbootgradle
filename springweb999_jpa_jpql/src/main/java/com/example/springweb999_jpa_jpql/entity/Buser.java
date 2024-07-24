package com.example.springweb999_jpa_jpql.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private int num;
	
	@Column(name="buser_name")
	private String bname;
	
	@Column(name="buser_loc")
	private String loc;
	
	@Column(name="buser_tel")
	private String btel;
	
	@OneToMany(mappedBy = "buser")
	private List<Jikwon> jList;
}
