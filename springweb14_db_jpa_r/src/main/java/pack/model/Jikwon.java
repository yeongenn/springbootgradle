package pack.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pack.model.Buser;

@Getter
@Setter

@Entity
@Table(name="jikwon")
public class Jikwon {

	@Id
	@Column(name="jikwon_no")
	private int no;
	
	@Column(name="jikwon_name")
	private String name;
	
	@Column(name="jikwon_jik")
	private String jik;
	
	@Column(name="jikwon_pay")
	private int pay;
	
//	@Column(name="buser_num")
//	private int buserNum;
	
	//JOIN
	@ManyToOne // 테이블 연관관계
	@JoinColumn(name="buser_num", referencedColumnName = "buser_no")
	private Buser buser;
	
	@Column(name="jikwon_gen")
	private String gender;
	
}
