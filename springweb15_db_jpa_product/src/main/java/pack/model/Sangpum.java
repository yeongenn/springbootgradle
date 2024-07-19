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
@Table(name="sangdata")
public class Sangpum {

	@Id
	private int code;
	
	@Column(nullable = false)
	private String sang;
	
	private int su, dan;
	
	
}
