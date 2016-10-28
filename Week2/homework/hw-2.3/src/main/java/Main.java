import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

public class Main {
    public static void main(String[] args) {
            MongoClient client = new MongoClient();
            MongoDatabase db = client.getDatabase("students");
            MongoCollection<Document> collection = db.getCollection("grades");

            try (MongoCursor<Document> documentsCursor = collection
                    .find(eq("type", "homework"))
                    .sort(ascending("student_id", "score"))
                    .iterator()) {

                Integer previousStudentId = null;

                while (documentsCursor.hasNext()) {
                    Document document = documentsCursor.next();
                    ObjectId id = document.getObjectId("_id");
                    Integer studentId = document.getInteger("student_id");

                    if (!Objects.equals(studentId, previousStudentId)) {
                        previousStudentId = studentId;
                        collection.deleteOne(eq("_id", id));
                    }
                }
            }

            System.out.println(collection.count()); // 600
    }
}
