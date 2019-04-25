package com.jiang.ssgp.service.impl;

import com.jiang.ssgp.domain.po.Project;
import com.jiang.ssgp.domain.po.Selection;
import com.jiang.ssgp.domain.po.Teacher;
import com.jiang.ssgp.domain.vo.SelectionVO;
import com.jiang.ssgp.repository.ProjectRepository;
import com.jiang.ssgp.repository.SelectionRepository;
import com.jiang.ssgp.repository.TeacherRepository;
import com.jiang.ssgp.service.SelectionService;
import org.springframework.stereotype.Service;

@Service
public class SelectionServiceImpl implements SelectionService {
    private final SelectionRepository selectionRepository;
    private final ProjectRepository projectRepository;
    private final TeacherRepository teacherRepository;

    public SelectionServiceImpl(SelectionRepository selectionRepository, ProjectRepository projectRepository, TeacherRepository teacherRepository) {
        this.selectionRepository = selectionRepository;
        this.projectRepository = projectRepository;
        this.teacherRepository = teacherRepository;
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
}
