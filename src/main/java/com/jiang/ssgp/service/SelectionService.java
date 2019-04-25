package com.jiang.ssgp.service;

import com.jiang.ssgp.domain.po.Selection;
import com.jiang.ssgp.domain.vo.SelectionVO;

public interface SelectionService {
    Selection save(Selection selection);

    SelectionVO findByStudentId(String studentId);
}
