package com.FJ28.oss.controller;

import com.FJ28.commonutils.R;
import com.FJ28.oss.service.OssService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
//import com.FJ28.common

@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    // 上传头像的方法：
    @PostMapping("upload")
    public R uploadOssFile(MultipartFile file){
        String url = OssService.uploadFileAvator(file);
        return R.ok().data("url", url);
    }
    // ossService.upLoadFileAvatar(file);
}
