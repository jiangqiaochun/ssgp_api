package com.jiang.ssgp.service.impl;

import com.jiang.ssgp.domain.po.Project;
import com.jiang.ssgp.repository.ProjectRepository;
import com.jiang.ssgp.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project saveByTeacherId(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> findByTeacherId(String teacherId) {
        return projectRepository.findByTeacherId(teacherId);
    }

    @Override
    public void deleteById(String projectId) {
        projectRepository.deleteById(projectId);
    }
}
