package pack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="board")
@Data
public class Board {
	@Id
	@Column(name="num")
	private int num;
	private int readcnt, gnum, onum, nested;
	private String name, pass, mail, title, cont, bip, bdate;
}
