package com.jiang.ssgp.service;

import com.jiang.ssgp.domain.po.Project;
import com.jiang.ssgp.domain.vo.ProjectVO;

import java.util.List;

public interface ProjectService {
    Project saveByTeacherId(Project project);

    List<Project> findByTeacherId(String teacherId);

    void deleteById(String projectId);

    List<ProjectVO> findAll(String searchCondition);
}
