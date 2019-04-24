package com.jiang.ssgp.service;

import com.jiang.ssgp.domain.po.Project;

import java.util.List;

public interface ProjectService {
    Project saveByTeacherId(Project project);

    List<Project> findByTeacherId(String teacherId);

    void deleteById(String projectId);
}
