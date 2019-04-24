package com.jiang.ssgp.service;

import com.jiang.ssgp.domain.po.Project;
import com.jiang.ssgp.domain.po.Teacher;
import com.jiang.ssgp.domain.po.User;

import java.util.List;

/**
 * @author jqc
 * @create 2019-04-03 19:44
 */
public interface TeacherService {
    Teacher save(Teacher teacher);

    List<Teacher> findAll();

    Teacher findById(String userId);

}