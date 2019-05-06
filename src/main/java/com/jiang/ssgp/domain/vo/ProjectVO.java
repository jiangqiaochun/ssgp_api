package com.jiang.ssgp.domain.vo;

import lombok.Data;

@Data
public class ProjectVO {
    private String projectId;
    private String projectName;
    private String projectNature;
    private String projectType;
    private String teacherName;
    private String selectedStudentName;

    public ProjectVO (){}
    public ProjectVO (String projectId, String projectName, String projectNature, String projectType){
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectNature = projectNature;
        this.projectType = projectType;
    }
}
