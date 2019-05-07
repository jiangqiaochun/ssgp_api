package com.jiang.ssgp.domain.po;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Project {
    @Id
    private String id;
    private String projectName;
    private String projectType;
    private String projectNature;
    private String teacherId;
    private String status;
}
