package pack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table
public class Jikwon {
	
	/*
	 변수명 카멜표기법으로 적으면
	 JPA가 언더바로 바꿔서 파싱
	 jikwonRating -> jikwon_rating
	 buserNum -> buser_num
	 */
    
    @Id
    @Column(name="jikwon_no")
    private int no;
    
    @Column(name="jikwon_name")
    private String name;
    
    //@Column(name="jikwon_jik")
    private String jikwonJik;
    
    //@Column(name="jikwon_rating")
    private String jikwonRating;
    
    //@Column(name="buser_num")
    private int buserNum;
    
    @Column(name="jikwon_pay")
    private int pay;
}
