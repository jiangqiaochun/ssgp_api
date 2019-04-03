package com.jiang.ssgp.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author jqc
 * @create 2019-03-28 19:36
 */
public interface UploadService {
    boolean uploadStudents(MultipartFile file);

    boolean uploadTeachers(MultipartFile file);
}