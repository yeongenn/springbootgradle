package pack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Jikwon {

	@Id
	private Long jikwonNo;
	private String jikwonName;
}
