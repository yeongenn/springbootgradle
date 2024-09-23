package pack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mongodb.client.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;

@Controller
public class TestController {
	
	@GetMapping("/show")
	public String process (Model model) {
		List<KaData> kaDataList = new ArrayList<KaData>();
		
		//GridFSBucket gridFSBucket = GridFSBuckets.create(mongoClient.getDatabase("katalkDB"), "katalkFiles");
		
		try {
			// GridFS
			
		} catch (Exception e) {
			System.out.println("process error : " + e);
		}
		
		model.addAttribute("datalist", kaDataList);
		return "show";
	}

}
