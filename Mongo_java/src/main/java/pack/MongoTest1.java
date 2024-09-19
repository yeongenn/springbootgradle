package pack;

import java.util.function.Consumer;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoTest1 {

	public static void main(String[] args) {
		// Application project : java + MongoDB
		try {
			MongoClient client = MongoClients.create("mongodb://localhost:27017");
			MongoDatabase db = client.getDatabase("test");
			MongoCollection<Document> collection = db.getCollection("customer");
			System.out.println("자료 건수 : " + collection.countDocuments());
			
			// 한 개의 Document 읽기
			Document document = collection.find().first();
			System.out.println("첫번째 자료 : " + document.toJson());
		
			System.out.println("전체 자료");
//			FindIterable<Document> iter = collection.find();
//			MongoCursor<Document> cursor = iter.iterator();
			
			MongoCursor<Document> cursor = collection.find().iterator();
			//MongoCursor<Document> cursor = collection.find().limit(2).iterator();
			
			while(cursor.hasNext()) {	// 자료가 있는 동안 반복
//				Document doc = cursor.next();
//				String jsonResult = doc.toJson();
				
				String jsonResult = cursor.next().toJson();
				System.out.println(jsonResult);
			}
			
			System.out.println("전체 자료 : field 자료만 출력");
			cursor = collection.find().iterator();
			while(cursor.hasNext()) {
				Document doc2 = cursor.next();
				System.out.println("이름 : " + doc2.get("name") + ", 나이 : " + doc2.get("age") + ", 성별 : " + doc2.get("gender"));
			}
			
			System.out.println("----------------------------------------------------");
			collection.find().forEach(printData);
		} catch (Exception e) {
			System.out.println("err : " + e.getMessage());
		}

	}
	
	// Block<Document> 대신 Consumer<Document>를 사용
	static Consumer<Document> printData = new Consumer<Document>() {
		public void accept(Document d) {
			System.out.println(d.toJson());
		};
	};

}
