package com.example.springweb999_jpa_jpql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Jikwon {

	@Id
	@Column(name="jikwon_no")
	private int no;
	
	@Column(name="jikwon_name")
	private String name;
	
	@Column(name="jikwon_jik")
	private String jik;
	
	@Column(name="jikwon_gen")
	private String gender;
	
	@Column(name="jikwon_pay")
	private int pay;
	
	@ManyToOne
	@JoinColumn(name="buser_num", referencedColumnName = "buser_no")
	private Buser buser;
}
