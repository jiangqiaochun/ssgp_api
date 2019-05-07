package com.jiang.ssgp.service;

import com.jiang.ssgp.domain.po.Student;
import com.jiang.ssgp.domain.po.User;

import java.util.List;

/**
 * @author jqc
 * @create 2019-03-28 19:43
 */
public interface StudentService {
    Student save(Student student);

    Student findById(String userId);

    List<Student> findAll();

    void deleteAll();

    void deleteById(String studentId);
}