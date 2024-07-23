package pack.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name="springboard")
public class Board {
	
	@Id
    private int num;
    
    private String author, title, content;
    private Timestamp bwrite;
    private int readcnt;
}
