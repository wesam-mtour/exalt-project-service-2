package com.exalt.partssystem.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "someChangeWithMongoDatabase", author = "testAuthor")
    public void someChange2(MongoDatabase db) {
        MongoCollection<Document> mycollection = db.getCollection("company");
        Document doc = new Document("testName", "example").append("test", "1");
        mycollection.insertOne(doc);
    }

}
