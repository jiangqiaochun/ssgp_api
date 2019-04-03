package com.jiang.ssgp.repository;

import com.jiang.ssgp.domain.po.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jqc
 * @create 2019-03-28 19:45
 */
@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}