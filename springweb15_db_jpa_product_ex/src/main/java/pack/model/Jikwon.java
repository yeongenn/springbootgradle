package pack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Jikwon {

	@Id
	@Column(name="jikwon_no")
	private int no;
	
	@Column(name="jikwon_name")
	private String name;
	
	@Column(name="jikwon_gen")
	private String gender;
	
	@Column(name="jikwon_pay")
	private int pay;
	
	@Column(name="jikwon_jik")
	private String jik;
}
