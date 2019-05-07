package com.jiang.ssgp.service.impl;

import com.jiang.ssgp.domain.po.Student;
import com.jiang.ssgp.repository.StudentRepository;
import com.jiang.ssgp.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jqc
 * @create 2019-03-28 19:44
 */
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findById(String userId) {
        return studentRepository.findById(userId).orElse(null);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @Override
    public void deleteById(String studentId) {
        studentRepository.deleteById(studentId);
    }
}