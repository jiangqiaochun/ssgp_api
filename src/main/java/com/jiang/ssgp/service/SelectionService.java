package com.jiang.ssgp.service;

import com.jiang.ssgp.domain.po.Selection;
import com.jiang.ssgp.domain.vo.SelectionVO;

import java.util.List;

public interface SelectionService {
    Selection save(Selection selection);

    SelectionVO findByStudentId(String studentId);

    List<SelectionVO> findAll();

    void delete(String id);

    void examine(String id);
}
