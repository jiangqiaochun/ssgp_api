package com.jiang.ssgp.adviser;

import com.jiang.ssgp.domain.vo.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity UsernameNotFoundExceptionHandler(UsernameNotFoundException e ){
        Result result = new Result();
        result.setCode(400);
        result.setMessage(e.getMessage());
        return ResponseEntity.ok(result);
    }
}
