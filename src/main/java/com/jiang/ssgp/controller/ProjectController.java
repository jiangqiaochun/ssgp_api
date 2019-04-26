package com.jiang.ssgp.controller;

import com.jiang.ssgp.domain.po.Project;
import com.jiang.ssgp.domain.vo.ProjectVO;
import com.jiang.ssgp.domain.vo.Result;
import com.jiang.ssgp.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private Logger log = LoggerFactory.getLogger(ProjectController.class);
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/{teacherId}")
    public ResponseEntity saveById(@PathVariable String teacherId,
                                   @RequestParam String projectName,
                                   @RequestParam String projectType,
                                   @RequestParam String projectNature){
        log.info("老师" + teacherId + "新建课题");
        Result result = new Result();
        Project project = new Project();
        project.setProjectName(projectName);
        project.setProjectType(projectType);
        project.setProjectNature(projectNature);
        project.setTeacherId(teacherId);
        project = projectService.saveByTeacherId(project);
        result.setData(project);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity getByTeacherId(@PathVariable String teacherId){
        log.info("获取" + teacherId + "的课题列表");
        Result result = new Result();
        List<ProjectVO> projectVOList = projectService.findByTeacherId(teacherId);
        result.setData(projectVOList);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity deleteById(@PathVariable String projectId){
        log.info("删除项目：" + projectId);
        Result result = new Result();
        projectService.deleteById(projectId);
        result.setMessage("删除成功！");
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity findAll(@RequestParam(required = false) String searchCondition){
        log.info("获取所有毕业论文列表, 搜索条件为：" + searchCondition);
        Result result = new Result();
        List<ProjectVO> projectVOList = projectService.findAll(searchCondition);
        result.setData(projectVOList);
        return ResponseEntity.ok(result);
    }

}
