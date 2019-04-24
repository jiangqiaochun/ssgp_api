package com.jiang.ssgp.repository;

import com.jiang.ssgp.domain.po.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByTeacherId(String teacherId);
}
