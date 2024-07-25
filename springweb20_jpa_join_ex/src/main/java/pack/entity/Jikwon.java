package pack.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Jikwon {

	@Id
	@Column(name = "jikwon_no")
	private int jno;

	@Column(name = "jikwon_name")
	private String jname;

	@Column(name = "jikwon_jik")
	private String jik;

	@Column(name = "jikwon_pay")
	private int pay;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buser_num")
	private Buser buser;

	@OneToMany(mappedBy = "jikwon", fetch = FetchType.EAGER)
	private List<Gogek> gogekList;

}
