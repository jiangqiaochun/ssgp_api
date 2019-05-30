package com.jiang.ssgp.domain.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jqc
 * @create 2019-03-18 17:05
 */
@Data
public class User {
    @Field("_id")
    private String id;
    private String userName;
    private String phoneNum;
    private String password;
    private String character;
    @JsonIgnore
    private Set<Authority> authorities = new HashSet<>();
}