package pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonController2 {
	
	@GetMapping("list2")
	@ResponseBody
	public Map<String, Object> getJsons(){ // 복수의 자료를 리턴할 때는 key-value값 형태로 -> Map
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "루피");
		data.put("age", "11");
		dataList.add(data);
		
		data = new HashMap<String, String>();
		data.put("name", "ye");
		data.put("age", "00");
		dataList.add(data);
		
		data = new HashMap<String, String>();
		data.put("name", "도레미");
		data.put("age", "999");
		dataList.add(data);
		//System.out.println("data : " + data);
		//return data; // 이렇게 하면 마지막 데이터 하나가 넘어가겠지?
		// 근데 나는 데이터 3개 다 넘기고 싶은데?
		
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("datas", dataList);
		//System.out.println("dataList : " + dataList);
		return data2; // 데이터 3개가 다 넘어간다~!
		
		
		
	}
}
