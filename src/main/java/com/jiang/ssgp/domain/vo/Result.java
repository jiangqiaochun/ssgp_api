package com.jiang.ssgp.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jqc
 * @create 2019-03-18 18:52
 */
@Data
public class Result<T> implements Serializable {
    Integer code = 200;
    String message;
    T data;

    public static Result authFailed(){
        Result result = new Result();
        result.setCode(400);
        result.setMessage("登录失败！");
        return result;
    }
}