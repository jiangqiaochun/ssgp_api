package com.jiang.ssgp.controller;

import com.jiang.ssgp.domain.vo.Result;
import com.jiang.ssgp.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jqc
 * @create 2019-03-28 19:35
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    private final static Logger log = LoggerFactory.getLogger(UploadController.class);

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/students")
    public ResponseEntity uploadStudents(@RequestParam MultipartFile file){
        log.info("上传学生信息表");
        Result result = new Result();
        if(file.isEmpty()){
            result.setCode(400);
            result.setMessage("请选择文件！");
            return  ResponseEntity.ok(result);
        }
        boolean success = uploadService.uploadStudents(file);
        if( success ) {
            result.setMessage("上传成功！");
        } else {
            result.setCode(500);
            result.setMessage("上传失败！");
        }
        return ResponseEntity.ok(result);
    }
}