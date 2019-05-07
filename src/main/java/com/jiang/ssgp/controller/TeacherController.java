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

    @GetMapping("/{teacherId}")
    public ResponseEntity findByTeacherId(@PathVariable String teacherId){
        Result result = new Result();
        Teacher teacher = teacherService.findById(teacherId);
        result.setData(teacher);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{teacherId}")
    public ResponseEntity changePhone(@PathVariable String teacherId,
                                      @RequestParam String newPhone){
        log.info("老师" + teacherId + "修改新号码：" + newPhone);
        Result result = new Result();
        Teacher teacher = teacherService.findById(teacherId);
        teacher.setPhoneNum(newPhone);
        teacher = teacherService.save(teacher);
        result.setData(teacher);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping()
    public ResponseEntity deleteAll(){
        log.info("删除所有老师列表");
        Result result = new Result();
        teacherService.deleteAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity deleteById(@PathVariable String teacherId){
        log.info("删除工号为：" + teacherId + "的老师");
        Result result = new Result();
        teacherService.deleteById(teacherId);
        return ResponseEntity.ok(result);
    }

}