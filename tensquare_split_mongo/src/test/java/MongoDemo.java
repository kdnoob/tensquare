import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDemo {

    public static void main(String[] args) {


        MongoClient mongoClient = new MongoClient("192.168.63.128");
        MongoDatabase splitdb = mongoClient.getDatabase("spitdb");
        MongoCollection<Document> spit = splitdb.getCollection("spit");

//        FindIterable<Document> documents = spit.find();

//        BasicDBObject bson = new BasicDBObject("userid","1013");
//        FindIterable<Document> documents = spit.find(bson);

        BasicDBObject bson = new BasicDBObject("visits", new BasicDBObject("$lt", 1000));
        FindIterable<Document> documents = spit.find(bson);

        for (Document document : documents) {
            System.out.println(document.toString());
//            System.out.print("id："+ document.getString("_id"));
            System.out.print("内容："+ document.getString("content"));
            System.out.print("用户ID:"+document.getString("userid"));
            System.out.println("浏览量："+document.getInteger("visits"));

        }

        mongoClient.close();

    }

}
