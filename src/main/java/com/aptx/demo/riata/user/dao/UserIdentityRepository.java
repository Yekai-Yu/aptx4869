package com.aptx.demo.riata.user.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.aptx.demo.riata.user.model.UserIdentity;

import java.util.Optional;

public interface UserIdentityRepository extends MongoRepository<UserIdentity, ObjectId> {
    Optional<UserIdentity> findByuSignature(String signature);
}
