package pack.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JikwonDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JikwonRepository  jikwonRepository;
    
    public List<Jikwon> list(int buserNum, String rating){
    	List<Jikwon> jikwonList = null;
    	try {
    		if(rating.equals("all")) {
                jikwonList = jikwonRepository.findByBuserNum(buserNum);
            } else {
                jikwonList = jikwonRepository.findByBuserNumAndJikwonRating(buserNum, rating);
            }
        } catch (Exception e) {
            logger.info("list err : " + e);
        }
    	
    	return jikwonList;
    }

    
}
