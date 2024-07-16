package pack;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component
@Repository
public class DataSource extends DriverManagerDataSource{
	public DataSource() {
		setDriverClassName("org.mariadb.jdbc.Driver");
		setUrl("jdbc:mariadb://localhost:3306/test");
		setUsername("root");
		setPassword("123");
		
	}
}
