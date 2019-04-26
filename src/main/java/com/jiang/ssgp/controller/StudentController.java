package com.jiang.ssgp.controller;

import com.jiang.ssgp.domain.po.Student;
import com.jiang.ssgp.domain.vo.Result;
import com.jiang.ssgp.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jqc
 * @create 2019-03-28 19:43
 */
@RestController
@RequestMapping("/students")
public class StudentController {
    private final static Logger log = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity findAll(){
        log.info("查找所有学生列表");
        Result result = new Result();
        List<Student> studentList = studentService.findAll();
        result.setData(studentList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity findById(@PathVariable String studentId){
        log.info("查找学生" + studentId + "的个人信息");
        Result result = new Result();
        Student student = studentService.findById(studentId);
        result.setData(student);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{studentId}")
    public ResponseEntity changePhone(@PathVariable String studentId,
                                      @RequestParam String newPhone){
        log.info("学生" + studentId + "修改新号码：" + newPhone);
        Result result = new Result();
        Student student = studentService.findById(studentId);
        student.setPhoneNum(newPhone);
        student = studentService.save(student);
        result.setData(student);
        return ResponseEntity.ok(result);
    }
}