package pack.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name="product")
public class ProductVo {

	@jakarta.persistence.Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//Indicates that the persistence provider must assign pks for the entity using a database identity column
	private int code;
	
	@Column(nullable = false, length = 20)
	private String sang;
	
	private int su;
	private int dan;
}
