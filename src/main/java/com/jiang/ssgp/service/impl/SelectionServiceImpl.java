package com.jiang.ssgp.service.impl;

import com.jiang.ssgp.domain.po.Project;
import com.jiang.ssgp.domain.po.Selection;
import com.jiang.ssgp.domain.po.Student;
import com.jiang.ssgp.domain.po.Teacher;
import com.jiang.ssgp.domain.vo.SelectionVO;
import com.jiang.ssgp.repository.ProjectRepository;
import com.jiang.ssgp.repository.SelectionRepository;
import com.jiang.ssgp.repository.StudentRepository;
import com.jiang.ssgp.repository.TeacherRepository;
import com.jiang.ssgp.service.SelectionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectionServiceImpl implements SelectionService {
    private final SelectionRepository selectionRepository;
    private final ProjectRepository projectRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public SelectionServiceImpl(SelectionRepository selectionRepository, ProjectRepository projectRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.selectionRepository = selectionRepository;
        this.projectRepository = projectRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Selection save(Selection selection) {
        return selectionRepository.save(selection);
    }

    @Override
    public SelectionVO findByStudentId(String studentId) {
        SelectionVO selectionVO = new SelectionVO();
        Selection selection = selectionRepository.findByStudentId(studentId);
        Project project = projectRepository.findById(selection.getProjectId()).orElse(null);
        Teacher teacher = teacherRepository.findById(project.getTeacherId()).orElse(null);
        selectionVO.setId(selection.getId());
        selectionVO.setProjectId(project.getId());
        selectionVO.setProjectName(project.getProjectName());
        selectionVO.setProjectNature(project.getProjectNature());
        selectionVO.setProjectType(project.getProjectType());
        selectionVO.setTeacherName(teacher.getTeacherName());
        selectionVO.setTeacherJobTitle(teacher.getJobTitle());
        selectionVO.setTeacherPhoneNum(teacher.getPhoneNum());
        return selectionVO;
    }

    @Override
    public List<SelectionVO> findAll() {
        List<Selection> selectionList = selectionRepository.findAll();
        List<SelectionVO> selectionVOList = new ArrayList<>();
        for( Selection selection : selectionList){
            SelectionVO selectionVO = new SelectionVO();
            selectionVO.setId(selection.getId());
            Student student = studentRepository.findById(selection.getStudentId()).orElse(null);
            selectionVO.setStudentId(student.getId());
            selectionVO.setStudentName(student.getStudentName());
            selectionVO.setStudentClassNum(student.getClassNum());
            selectionVO.setStudentPhoneNum(student.getPhoneNum());
            Project project = projectRepository.findById(selection.getProjectId()).orElse(null);
            selectionVO.setProjectId(project.getId());
            selectionVO.setProjectType(project.getProjectType());
            selectionVO.setProjectNature(project.getProjectNature());
            selectionVO.setProjectName(project.getProjectName());
            Teacher teacher = teacherRepository.findById(project.getTeacherId()).orElse(null);
            selectionVO.setTeacherName(teacher.getTeacherName());
            selectionVO.setTeacherPhoneNum(teacher.getPhoneNum());
            selectionVO.setTeacherJobTitle(teacher.getJobTitle());
            selectionVOList.add(selectionVO);
        }
        return selectionVOList;
    }
}
