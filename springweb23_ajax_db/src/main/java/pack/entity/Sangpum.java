package pack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name="sangdata")
public class Sangpum {
	
	@Id
	private int code;
	private String sang, su, dan;

}
