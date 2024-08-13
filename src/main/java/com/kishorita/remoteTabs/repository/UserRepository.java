package com.kishorita.remoteTabs.repository;

import com.kishorita.remoteTabs.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
    User findByUserNameAndKey(String username, String key);
}
