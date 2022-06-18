package com.FJ28.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    public String uploadFileAvator(MultipartFile file);

}
