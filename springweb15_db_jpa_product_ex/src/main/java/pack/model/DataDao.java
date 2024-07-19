package pack.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataDao {

	@Autowired
	private JikwonRepository repository;
	
	public List<Jikwon> getJikwonByJik(String jik){
		List<Jikwon> jikwonList = repository.findByJik(jik);
		return jikwonList;		
	}
}
