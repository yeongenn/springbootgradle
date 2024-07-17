package pack.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataDao {

	@Autowired
	private JikwonMapper jikMapper;
	
	@Autowired
	private GogekMapper gogekMapper;
	
	public List<JikwonDto> getJikwonAll(){
		List<JikwonDto> jikwonList = jikMapper.selectAll();
		return jikwonList;
	}
	
	public List<GogekDto> getMyGogek(String no){
		List<GogekDto> gogekList = gogekMapper.selectMyGogek(no);
		return gogekList;
	}
}
