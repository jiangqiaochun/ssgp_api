package com.jiang.ssgp.domain.po;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class SystemOpenTime {
    @Id
    private String id;
    private String startTime;
    private String endTime;
}
