package course;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class BlogPostDAO {
    MongoCollection<Document> postsCollection;

    public BlogPostDAO(final MongoDatabase blogDatabase) {
        postsCollection = blogDatabase.getCollection("posts");
    }

    public Document findByPermalink(String permalink) {
        return postsCollection.find().filter(Filters.eq("permalink", permalink)).first();
    }

    public List<Document> findByDateDescending(int limit) {

        return this.postsCollection.find()
                .limit(limit)
                .sort(descending("date"))
                .into(new ArrayList<Document>());
    }


    public String addPost(String title, String body, List tags, String username) {

        System.out.println("inserting blog entry " + title + " " + body);

        String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase();

        this.postsCollection.insertOne(new Document()
                .append("author", username)
                .append("body", body)
                .append("permalink", permalink)
                .append("tags", tags)
                .append("comments", Collections.emptyList())
                .append("date", new Date())
                .append("title", title)
        );

        return permalink;
    }

    // Append a comment to a blog post
    public void addPostComment(final String name, final String email, final String body,
                               final String permalink) {

        Document comment = new Document("author", name).append("body", body);

        if (name == null)
        {
            return;
        }

        comment = email != null ? comment.append("email", email) : comment.append("email", "");

        this.postsCollection.updateOne(findByPermalink(permalink), Updates.push("comments", comment));
    }
}
