package com.jiang.ssgp.domain.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jqc
 * @create 2019-04-03 19:41
 */
@Data
public class Teacher {
    @Id
    private String id;
    private String teacherName;
    private String password = new String("123456");
    private String jobTitle;
    private String phoneNum;
    private String character = "Teacher";
    @JsonIgnore
    private Set<Authority> authorities = new HashSet<>();
}