package com.jiang.ssgp.controller;

import com.jiang.ssgp.domain.po.User;
import com.jiang.ssgp.domain.vo.Result;
import com.jiang.ssgp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jqc
 * @create 2019-03-18 17:41
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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


}