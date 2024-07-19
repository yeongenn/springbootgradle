package pack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Mem {

	@Id
	private int num;
	
	private String name, addr;
}
