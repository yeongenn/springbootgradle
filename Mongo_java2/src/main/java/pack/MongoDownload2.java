package pack;

import java.io.ByteArrayOutputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.bson.types.ObjectId;

import com.mongodb.MongoGridFSException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;

public class MongoDownload2 {

	public static void main(String[] args) {
		String connString = "mongodb://localhost:27017";

		try (MongoClient mongoClient = MongoClients.create(connString)) {
			MongoDatabase database = mongoClient.getDatabase("katalkDB");

			GridFSBucket gridFSBucket = GridFSBuckets.create(database, "katalkFiles");

			// 모든 파일의 ObjectId 얻기
			MongoCursor<GridFSFile> cursor = gridFSBucket.find().iterator();

			while (cursor.hasNext()) {
				GridFSFile gridFSFile = cursor.next();
				ObjectId field = gridFSFile.getObjectId();

				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				try {
					gridFSBucket.downloadToStream(field, outputStream);
					String fileContent = new String(outputStream.toByteArray());
					// System.out.println("ObjectId " + field.toHexString() + " : " + fileContent);

					// JSON 데이터 파싱
					if (fileContent.trim().startsWith("[")) {
						// 파일 내용이 JSON 배열이면 [로 시작할 것이므로 배열로 처리
						JSONArray jsonArray = new JSONArray(fileContent);

						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject jsonObject = jsonArray.getJSONObject(i);

							String req = jsonObject.getString("req");
							String res = jsonObject.getString("res");

							System.out.println("질문 : " + req + " ~ 대답 : " + res);
						}
					} else { // JSON 객체가 1개인 경우
						JSONObject jsonObject = new JSONObject(fileContent);

						String req = jsonObject.getString("req");
						String res = jsonObject.getString("res");

						System.out.println("질문 : " + req + " ~ 대답 : " + res);
					}
				} catch (MongoGridFSException e) {
					System.out.println("ObjectId 읽기 에러 : " + field.toHexString());
				}
			}
		} catch (Exception e) {
			System.out.println("에러 : " + e);
		}
	}

}
