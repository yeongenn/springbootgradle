package pack;

import java.io.ByteArrayOutputStream;

import org.json.JSONObject;
import org.bson.types.ObjectId;

import com.mongodb.MongoGridFSException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;

public class MongoDownload1 {

   public static void main(String[] args) {
      //분산 저장된 MongoDB 자료 부분 읽기
      String connString = "mongodb://localhost:27017";
      
      try(MongoClient mongoClient = MongoClients.create(connString)) {
         GridFSBucket fsBucket = GridFSBuckets.create(mongoClient.getDatabase("katalkDB"),"katalkFiles");
         
         ObjectId[] fields = {
               new ObjectId("66ecf49d6e1db8017655189f"),
               new ObjectId("66ecf49d6e1db801765518a1"),
               new ObjectId("66ecf49d6e1db801765518a3")
         };
         
         for(ObjectId field : fields) {
            downloadAndPrint(fsBucket, field);
         }
      } catch (Exception e) {
         System.out.println(e);
      }
      
   }
   
   //파일을 읽기 (다운로드)하고 그 내용을 출력하기 위한 메서드
   private static void downloadAndPrint(GridFSBucket gridFSBucket, ObjectId objectId) {
      try {
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         gridFSBucket.downloadToStream(objectId, outputStream);
         
         String fileContent = new String(outputStream.toByteArray());
         
         System.out.println("fileContent for files_id : " + objectId +" -> "+fileContent);
         
         //Json parsing
         JSONObject jsonObject = new JSONObject(fileContent);
         String req = jsonObject.getString("req");
         String res = jsonObject.getString("res");
         
         System.out.println("질문 : " + req + " ~ 대답 : " + res);
         
      } catch (MongoGridFSException e) {
         System.out.println("파일 찾기 실패 : " + e);
      } catch (Exception e) {
         System.out.println("downloadAndprint err : " + e);
      }
   }
}
