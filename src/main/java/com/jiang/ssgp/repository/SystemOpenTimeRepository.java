package com.jiang.ssgp.repository;

import com.jiang.ssgp.domain.po.SystemOpenTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemOpenTimeRepository extends MongoRepository<SystemOpenTime, String> {
}
