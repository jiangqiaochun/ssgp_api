package com.jiang.ssgp.service.impl;

import com.jiang.ssgp.domain.po.Teacher;
import com.jiang.ssgp.repository.TeacherRepository;
import com.jiang.ssgp.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jqc
 * @create 2019-04-03 19:44
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}