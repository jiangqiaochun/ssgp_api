package com.jiang.ssgp.service.impl;

import com.jiang.ssgp.domain.po.Project;
import com.jiang.ssgp.domain.po.Teacher;
import com.jiang.ssgp.domain.vo.ProjectVO;
import com.jiang.ssgp.repository.ProjectRepository;
import com.jiang.ssgp.repository.TeacherRepository;
import com.jiang.ssgp.service.ProjectService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final TeacherRepository teacherRepository;
    private final MongoTemplate mongoTemplate;

    public ProjectServiceImpl(ProjectRepository projectRepository, TeacherRepository teacherRepository, MongoTemplate mongoTemplate) {
        this.projectRepository = projectRepository;
        this.teacherRepository = teacherRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Project saveByTeacherId(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<ProjectVO> findByTeacherId(String teacherId) {
        List<Project> projects = projectRepository.findByTeacherId(teacherId);
        List<ProjectVO> projectVOList = new ArrayList<>();
        for (Project project : projects) {
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

    @Override
    public void deleteById(String projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public List<ProjectVO> findAll(String searchCondition) {
        List<ProjectVO> projectVOList = new ArrayList<>();
        List<Project> projectList;
        if( null == searchCondition) {
            projectList = projectRepository.findAll();
        }else{
            Criteria criteria = Criteria.where("projectName").regex(".*" + searchCondition + ".*");
            Query query = new Query(criteria);
            projectList = mongoTemplate.find(query, Project.class);
        }
        for (Project project : projectList) {
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
