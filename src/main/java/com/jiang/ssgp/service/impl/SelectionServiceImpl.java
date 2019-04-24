package com.jiang.ssgp.service.impl;

import com.jiang.ssgp.domain.po.Selection;
import com.jiang.ssgp.repository.SelectionRepository;
import com.jiang.ssgp.service.SelectionService;
import org.springframework.stereotype.Service;

@Service
public class SelectionServiceImpl implements SelectionService {
    private final SelectionRepository selectionRepository;

    public SelectionServiceImpl(SelectionRepository selectionRepository) {
        this.selectionRepository = selectionRepository;
    }

    @Override
    public Selection save(Selection selection) {
        return selectionRepository.save(selection);
    }
}
