package com.jiang.ssgp.controller;

import com.jiang.ssgp.domain.po.User;
import com.jiang.ssgp.domain.vo.Result;
import com.jiang.ssgp.security.MyAuthenticationProvider;
import com.jiang.ssgp.security.SecurityUtils;
import com.jiang.ssgp.service.StudentService;
import com.jiang.ssgp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jqc
 * @create 2019-03-18 18:54
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final MyAuthenticationProvider myAuthenticationProvider;
    private final StudentService studentService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, MyAuthenticationProvider myAuthenticationProvider, StudentService studentService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.myAuthenticationProvider = myAuthenticationProvider;
        this.studentService = studentService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam String userId,
                                @RequestParam String password){
        log.info("登录认证");
        Result result = new Result();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);
        Authentication authentication = myAuthenticationProvider.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if( null == authentication){
            result.setCode(400);
            result.setMessage("用户名或密码错误！");
            return ResponseEntity.ok(result);
        }
        if( null != SecurityUtils.getCurrentUserId()){
            User user = userService.findById(userId);
            if( null != user){
                result.setData(user);
            } else if ( null != studentService.findById(userId)){
                result.setData(studentService.findById(userId));
            } else{
                result.setCode(500);
                result.setMessage("用户不存在！");
            }
        }
        return ResponseEntity.ok(result);
    }
}