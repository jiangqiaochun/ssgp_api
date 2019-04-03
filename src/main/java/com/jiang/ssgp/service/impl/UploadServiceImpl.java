package com.jiang.ssgp.service.impl;

import com.jiang.ssgp.domain.po.Student;
import com.jiang.ssgp.domain.po.Teacher;
import com.jiang.ssgp.service.StudentService;
import com.jiang.ssgp.service.TeacherService;
import com.jiang.ssgp.service.UploadService;
import com.jiang.ssgp.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jqc
 * @create 2019-03-28 19:37
 */
@Service
public class UploadServiceImpl implements UploadService {
    private final StudentService studentService;
    private final TeacherService teacherService;

    public UploadServiceImpl(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public boolean uploadStudents(MultipartFile file) {
        List<ArrayList<String>> readResult ;
        try {

            //判断文件是否为空
            if (file.isEmpty()) {
                return false;
            }
            //判断文件大小
            long size = file.getSize();
            String name = file.getOriginalFilename();
            if (StringUtils.isBlank(name) || size == 0) {
                return false;
            }
            //获取文件后缀
            String postfix = ExcelUtil.getPostfix(name);


            //读取文件内容
            if (StringUtils.equals("xlsx", postfix)) {
                readResult = ExcelUtil.readXlsx(file);
            } else if (StringUtils.equals("xls", postfix)) {
                readResult = ExcelUtil.readXls(file);
            } else {
                return false;
            }
            if (readResult == null || readResult.size() == 0) {
                return false;
            }
            for (int i = 2; i<readResult.size(); i++) {
                Student student = new Student();
                student.setId(readResult.get(i).get(1));
                student.setStudentName(readResult.get(i).get(2));
                student.setClassNum(readResult.get(i).get(3));
                studentService.save(student);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean uploadTeachers(MultipartFile file) {
        List<ArrayList<String>> readResult ;
        try {

            //判断文件是否为空
            if (file.isEmpty()) {
                return false;
            }
            //判断文件大小
            long size = file.getSize();
            String name = file.getOriginalFilename();
            if (StringUtils.isBlank(name) || size == 0) {
                return false;
            }
            //获取文件后缀
            String postfix = ExcelUtil.getPostfix(name);


            //读取文件内容
            if (StringUtils.equals("xlsx", postfix)) {
                readResult = ExcelUtil.readXlsx(file);
            } else if (StringUtils.equals("xls", postfix)) {
                readResult = ExcelUtil.readXls(file);
            } else {
                return false;
            }
            if (readResult == null || readResult.size() == 0) {
                return false;
            }
            for (int i = 2; i<readResult.size(); i++) {
                Teacher teacher = new Teacher();
                teacher.setId(readResult.get(i).get(1));
                teacher.setTeacherName(readResult.get(i).get(2));
                teacher.setJobTitle(readResult.get(i).get(3));
                teacher.setPhoneNum(readResult.get(i).get(4));
                teacherService.save(teacher);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}