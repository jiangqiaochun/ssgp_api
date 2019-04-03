package com.jiang.ssgp.repository;

import com.jiang.ssgp.domain.po.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jqc
 * @create 2019-03-18 17:03
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}