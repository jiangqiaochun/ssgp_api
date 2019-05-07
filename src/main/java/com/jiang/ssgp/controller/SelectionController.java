package com.jiang.ssgp.controller;

import com.jiang.ssgp.domain.po.Selection;
import com.jiang.ssgp.domain.vo.Result;
import com.jiang.ssgp.domain.vo.SelectionVO;
import com.jiang.ssgp.service.SelectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        selection.setStatus("审核中");
        selection = selectionService.save(selection);
        result.setData(selection);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        log.info("删除id为：" + id + "的选题");
        Result result = new Result();
        selectionService.delete(id);
        result.setMessage("删除成功！");
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity examine(@PathVariable String projectId){
        log.info("审核课题：" + projectId);
        Result result = new Result();
        selectionService.examine(projectId);
        result.setMessage("success");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity findByStudentId(@PathVariable String studentId){
        log.info("查找学号" + studentId + "的选题");
        Result result = new Result();
        SelectionVO selectionVO = selectionService.findByStudentId(studentId);
        if(null == selectionVO){
            result.setCode(1001);
            result.setMessage("暂无选题");
        } else {
            result.setData(selectionVO);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity findAll(){
        log.info("查看所有选题情况列表");
        Result result = new Result();
        List<SelectionVO> selectionVOList = selectionService.findAll();
        result.setData(selectionVOList);
        return ResponseEntity.ok(result);
    }
}
