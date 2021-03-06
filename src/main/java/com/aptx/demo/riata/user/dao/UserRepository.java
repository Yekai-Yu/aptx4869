package com.aptx.demo.riata.user.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.aptx.demo.riata.user.model.UserDO;

public interface UserRepository extends MongoRepository<UserDO, ObjectId> {
}
