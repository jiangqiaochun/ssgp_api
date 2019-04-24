package com.jiang.ssgp.domain.po;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Selection {
    @Id
    private String id;
    private String studentId;
    private String projectId;
}
