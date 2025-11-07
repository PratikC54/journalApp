package com.PratikC54.journalApp.Repository;

import com.PratikC54.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User,ObjectId> {
    User findByUsername(String username);

    User deleteByUsername(String username);
}
