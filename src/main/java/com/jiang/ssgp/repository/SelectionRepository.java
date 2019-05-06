package com.jiang.ssgp.repository;

import com.jiang.ssgp.domain.po.Selection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectionRepository extends MongoRepository<Selection, String> {
    Selection findByStudentId(String studentId);

    Selection findByProjectId(String id);
}
