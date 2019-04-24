package com.jiang.ssgp.service.impl;

import com.jiang.ssgp.domain.po.Project;
import com.jiang.ssgp.domain.po.Teacher;
import com.jiang.ssgp.domain.vo.ProjectVO;
import com.jiang.ssgp.repository.ProjectRepository;
import com.jiang.ssgp.repository.TeacherRepository;
import com.jiang.ssgp.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final TeacherRepository teacherRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, TeacherRepository teacherRepository) {
        this.projectRepository = projectRepository;
        this.teacherRepository = teacherRepository;
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

    @Override
    public List<ProjectVO> findAll() {
        List<Project> projectList = projectRepository.findAll();
        List<ProjectVO> projectVOList =  new ArrayList<>();
        for( Project project : projectList){
            ProjectVO projectVO = new ProjectVO(project.getId(),
                    project.getProjectName(),
                    project.getProjectNature(),
                    project.getProjectType());
            Teacher teacher = teacherRepository.findById(project.getTeacherId()).orElse(null);
            projectVO.setTeacherName(teacher.getTeacherName());
            projectVOList.add(projectVO);
        }
        return projectVOList;
    }
}
