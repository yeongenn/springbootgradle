package pack;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

import static com.mongodb.client.model.Filters.eq;

public class MongoTest2 {
	
	// Customer 컬렉터를 저장하는 별도의 클래스 사용
	// 필드가 매우 동적일 경우에는 비추
	static class Customer {
		private ObjectId id;
		private String name;
		private int age;
		private String gender;

		public Customer(ObjectId id, String name, int age, String gender) {
			this.id = id;
			this.name = name;
			this.age = age;
			this.gender = gender;
		}

		@Override
		public String toString() {
			return "id = " + id + ", name : " + name + ", age : " + age + ", gender : " + gender;
		}
	}

	public static void main(String[] args) {
		String uri = "mongodb://localhost:27017";
		try (MongoClient client = MongoClients.create(uri)) {
			MongoDatabase db = client.getDatabase("test");
			MongoCollection<Document> collection = db.getCollection("customer");
			
			// 자료 추가
//			Document newData = new Document("name", "관우").append("age", "26").append("gender", "남");
//			collection.insertOne(newData);
			
			// 수정, 삭제를 위한 대상 자료 찾기
			Document findCustomer = collection.find(eq("name", "관우")).first();		// select * from customer where name = '관우' limit = 1
			if (findCustomer != null) {
				ObjectId custId = findCustomer.getObjectId("_id");
				
				
				// 자료 수정
//				collection.updateOne(eq("_id", custId), 
//						Updates.combine(Updates.set("age", "22"), Updates.set("gender", "남성")));
//				
//				System.out.println("수정 완료");
				
				
				// 자료 삭제
				collection.deleteOne(eq("_id", custId));
				System.out.println("삭제 성공");
				
				
			} else {
				System.out.println("해당 이름의 자료는 존재하지 않습니다.");
			}

			// 출력
			List<Customer> customers = new ArrayList<MongoTest2.Customer>();
			for (Document doc : collection.find()) {
				// age 필드가 Integer 또는 String 일 수 있으므로 이에 대한 처리
				Object ageObj = doc.get("age");
				int age = 0;
				if (ageObj instanceof Integer) {
					age = (Integer) ageObj;
				} else if (ageObj instanceof String) {
					try {
						age = Integer.parseInt((String) ageObj);	// String을 Integer로 반환
					} catch (Exception e) {
						System.out.println("invalid age format : " + ageObj);
					}
				}
				
				Customer customer = new Customer(doc.getObjectId("_id"), doc.getString("name"), age, doc.getString("gender"));
				
				customers.add(customer);
			}
			
			for (Customer customer : customers) {
				System.out.println(customer);
			}
			
			System.out.println("건수 : " + collection.countDocuments());
		} catch (Exception e) {
			System.out.println("error : " + e);
		}
	}

}
