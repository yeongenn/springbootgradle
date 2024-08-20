package pack.controller;

import java.time.LocalDate;
import lombok.Data;

@Data
public class BoardBean {
	private int num, readcnt, gnum, onum, nested;
	private String name, pass, mail, title, cont, bip, bdate;
	private String searchName, searchValue;
	
	public void setBdate() {
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		this.bdate = year + "-" + month + "-" + day;	
		// Timestamp.valueOf(LocalDateTime.now()).toString()
	}
}
