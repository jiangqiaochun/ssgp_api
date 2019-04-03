package com.jiang.ssgp.domain.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jqc
 * @create 2019-03-28 19:00
 */
@Data
public class Student {
    @Id
    private String id;
    private String studentName;
    private String password = new String("123456");
    private String classNum;
    private String phoneNum;
    private String character = "Student";
    @JsonIgnore
    private Set<Authority> authorities = new HashSet<>();
}