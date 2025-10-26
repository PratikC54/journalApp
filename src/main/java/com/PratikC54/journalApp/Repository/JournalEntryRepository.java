package com.PratikC54.journalApp.Repository;

import com.PratikC54.journalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntry,ObjectId> {

}
