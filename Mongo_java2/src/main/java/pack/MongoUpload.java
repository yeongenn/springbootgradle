package pack;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket; // 분산 저장을 위한 버킷 클래스
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.opencsv.CSVReader;

public class MongoUpload {

	public static void main(String[] args) {
		// 카카오톡(SNS) 데이터를 MongoDB에 저장
		String uri = "mongodb://localhost:27017";

		try (MongoClient client = MongoClients.create(uri)) {
			MongoDatabase databse = client.getDatabase("katalkDB");

			GridFSBucket girdFSBucket = GridFSBuckets.create(databse, "katalkFiles");

			// CSV 파일을 읽어 분산 저장
			ClassLoader classLoader = MongoUpload.class.getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream("katalkdata.csv");

			if (inputStream != null) {
				//System.out.println("성공");
				uploadCSV2Mongo(inputStream, girdFSBucket);
			} else {
				System.out.println("해당 파일이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void uploadCSV2Mongo(InputStream inputStream, GridFSBucket gridFSBucket) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				CSVReader csvReader = new CSVReader(reader)){
			
			// CSV 파일 내 모든 자료를 읽어 list로 저장
			List<String[]> records = csvReader.readAll();
			
			for(String[] record : records) {	// 각 행 반복
				// 각 행을 bson Document로 변환
				// 첫번째 필드를 req, 두번째 필드를 res
				Document doc = new Document("req", record[0]).append("res", record[1]);
				
				// 저장 옵션 설정 : 1MB(1024*1024) 크기의 묶음(chunk)으로 나누어 저장 <- 대용량 자료 저장 시 효과적
				GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1024 * 1024);
				
				// BSON Document를 JSON 형식의 바이트 배열로 변환
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(doc.toJson().getBytes());
				
				// gridFS를 사용해 MongoDB에 파일 업로드
				ObjectId field = gridFSBucket.uploadFromStream("katalkdata", byteArrayInputStream, options);
				
				System.out.println("ObjectId : " + field.toHexString()); 	// 저장된 파일의 ObjectId를 확인
			}
		} catch (Exception e) {
			System.out.println("uploadCSV2Mongo error : " + e);
		}
	}

}
