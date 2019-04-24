package com.jiang.ssgp.controller;

import com.jiang.ssgp.domain.po.Selection;
import com.jiang.ssgp.domain.vo.Result;
import com.jiang.ssgp.service.SelectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/selections")
public class SelectionController {
    private Logger log = LoggerFactory.getLogger(SelectionController.class);

    private final SelectionService selectionService;

    public SelectionController(SelectionService selectionService) {
        this.selectionService = selectionService;
    }

    @PostMapping()
    public ResponseEntity save(@RequestParam String studentId,
                               @RequestParam String projectId){
        log.info("学生" + studentId + "选择的题目为：" + projectId);
        Result result = new Result();
        Selection selection = new Selection();
        selection.setStudentId(studentId);
        selection.setProjectId(projectId);
        selection = selectionService.save(selection);
        result.setData(selection);
        return ResponseEntity.ok(result);
    }
}
