package com.jiang.ssgp.controller;

import com.jiang.ssgp.domain.po.Student;
import com.jiang.ssgp.domain.vo.Result;
import com.jiang.ssgp.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jqc
 * @create 2019-03-28 19:43
 */
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity findAll(){
        Result result = new Result();
        List<Student> studentList = studentService.findAll();
        result.setData(studentList);
        return ResponseEntity.ok(result);
    }
}