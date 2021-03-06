package com.jiang.ssgp.service.impl;

import com.jiang.ssgp.domain.po.Project;
import com.jiang.ssgp.domain.po.Selection;
import com.jiang.ssgp.domain.po.Student;
import com.jiang.ssgp.domain.po.Teacher;
import com.jiang.ssgp.domain.vo.ProjectVO;
import com.jiang.ssgp.repository.ProjectRepository;
import com.jiang.ssgp.repository.SelectionRepository;
import com.jiang.ssgp.repository.StudentRepository;
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
    private final SelectionRepository selectionRepository;
    private final StudentRepository studentRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, TeacherRepository teacherRepository, MongoTemplate mongoTemplate, SelectionRepository selectionRepository, StudentRepository studentRepository) {
        this.projectRepository = projectRepository;
        this.teacherRepository = teacherRepository;
        this.mongoTemplate = mongoTemplate;
        this.selectionRepository = selectionRepository;
        this.studentRepository = studentRepository;
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
            projectVO.setProjectStatus(project.getStatus());
            Teacher teacher = teacherRepository.findById(project.getTeacherId()).orElse(null);
            projectVO.setTeacherName(teacher.getTeacherName());
            Selection selection = selectionRepository.findByProjectId(project.getId());
            if( null != selection ){
                Student student = studentRepository.findById(selection.getStudentId()).orElse(null);
                projectVO.setSelectedStudentName(student.getStudentName());
                projectVO.setSelectionStatus(selection.getStatus());
            }
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
        Criteria criteria = Criteria.where("status").is("审核通过");
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(criteria);
        if( null != searchCondition) {
            Criteria criteria1 = Criteria.where("projectName").regex(".*" + searchCondition + ".*");
            criteriaList.add(criteria1);
        }
        Criteria[] criteriaArr = new Criteria[criteriaList.size()];
        criteriaList.toArray(criteriaArr);
        Query query = new Query(new Criteria().andOperator(criteriaArr));
        projectList = mongoTemplate.find(query, Project.class);
        for (Project project : projectList) {
            ProjectVO projectVO = new ProjectVO(project.getId(),
                    project.getProjectName(),
                    project.getProjectNature(),
                    project.getProjectType());
            Teacher teacher = teacherRepository.findById(project.getTeacherId()).orElse(null);
            Selection selection = selectionRepository.findByProjectId(project.getId());
            if( null != selection ){
                Student student = studentRepository.findById(selection.getStudentId()).orElse(null);
                projectVO.setSelectedStudentName(student.getStudentName());
            }
            projectVO.setTeacherName(teacher.getTeacherName());
            projectVOList.add(projectVO);
        }
        return projectVOList;
    }

    @Override
    public void examineSuccess(String projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        project.setStatus("审核通过");
        projectRepository.save(project);
    }

    @Override
    public Project findById(String projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }
}
