package pack.entity;

import java.util.Date;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Emp {

	@Id
	private Integer empno;

	private String ename;

	private String job;

	private Integer mgr;
	
	@Temporal(TemporalType.DATE) // 날짜 타입 맵핑시 사용
	private Date hiredate;
	
	private Double sal;
	
	private Double comm;
	
//	private Integer deptno;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="deptno", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private Dept dept;

}
