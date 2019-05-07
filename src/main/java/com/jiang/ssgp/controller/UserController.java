package com.jiang.ssgp.controller;

import com.jiang.ssgp.domain.po.SystemOpenTime;
import com.jiang.ssgp.domain.po.User;
import com.jiang.ssgp.domain.vo.Result;
import com.jiang.ssgp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jqc
 * @create 2019-03-18 17:41
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final StudentService studentService;
    private final SelectionService selectionService;
    private final ProjectService projectService;
    private final TeacherService teacherService;
    private final SystemOpenTimeService systemOpenTimeService;

    public UserController(UserService userService, StudentService studentService, SelectionService selectionService, ProjectService projectService, TeacherService teacherService, SystemOpenTimeService systemOpenTimeService) {
        this.userService = userService;
        this.studentService = studentService;
        this.selectionService = selectionService;
        this.projectService = projectService;
        this.teacherService = teacherService;
        this.systemOpenTimeService = systemOpenTimeService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity getById(@PathVariable String userId){
        log.info("获取个人信息" + userId);
        Result result = new Result();
        result.setData(userService.findById(userId));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{userId}")
    public ResponseEntity changePsw(@PathVariable String userId,
                                    @RequestParam String newPsw){
        log.info("管理员修改密码");
        Result result = new Result();
        result.setData(userService.changePsw(userId, newPsw));
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity save(@RequestParam String userId,
                               @RequestParam String password){
        log.info("添加管理员账号");
        Result result = new Result();
        User user = new User();
        user.setId(userId);
        user.setPassword(password);
        user.setCharacter("Admin");
        result.setData(userService.save(user));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/statistics")
    public ResponseEntity getStatistics(){
        log.info("获取数据统计");
        Result result = new Result();
        Map<String, Integer> map = new HashMap<>();
        map.put("studentCount", studentService.findAll().size());
        map.put("selectedStudent", selectionService.findAll().size());
        map.put("projectCount", projectService.findAll(null).size());
        map.put("selectedProject", selectionService.findAll().size());
        map.put("teacherCount", teacherService.findAll().size());
        result.setData(map);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/openTime")
    public ResponseEntity setOpenTime(@RequestParam String startTime,
                                      @RequestParam String endTime){
        log.info("设置系统开放时间");
        Result result = new Result();
        SystemOpenTime systemOpenTime = new SystemOpenTime();
        systemOpenTime.setStartTime(startTime);
        systemOpenTime.setEndTime(endTime);
        systemOpenTime = systemOpenTimeService.save(systemOpenTime);
        result.setData(systemOpenTime);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/openTime/{id}")
    public ResponseEntity getOpenTime(@PathVariable String id){
        log.info("获取系统开放时间");
        Result result = new Result();
        SystemOpenTime systemOpenTime = systemOpenTimeService.findOne(id);
        result.setData(systemOpenTime);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/openTime/{id}")
    public ResponseEntity updateById(@PathVariable String id,
                                     @RequestParam String startTime,
                                     @RequestParam String endTime){
        log.info("修改开放时间");
        Result result = new Result();
        SystemOpenTime systemOpenTime = systemOpenTimeService.findOne(id);
        systemOpenTime.setStartTime(startTime);
        systemOpenTime.setEndTime(endTime);
        systemOpenTime = systemOpenTimeService.save(systemOpenTime);
        result.setData(systemOpenTime);
        return ResponseEntity.ok(result);
    }


}