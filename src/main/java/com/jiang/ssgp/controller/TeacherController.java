package com.jiang.ssgp.controller;

import com.jiang.ssgp.domain.po.Project;
import com.jiang.ssgp.domain.po.Teacher;
import com.jiang.ssgp.domain.vo.Result;
import com.jiang.ssgp.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jqc
 * @create 2019-04-03 20:10
 */
@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping()
    public ResponseEntity findAll(){
        Result result = new Result();
        List<Teacher> teacherList = teacherService.findAll();
        result.setData(teacherList);
        return ResponseEntity.ok(result);
    }

}