package com.FJ28.oss.controller;

import com.FJ28.commonutils.R;
import com.FJ28.oss.service.OssService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
//import com.FJ28.common



@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired OssService ossService;
    // 上传头像的方法：
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R uploadOssFile(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file){
        String url = ossService.uploadFileAvator(file);

        // return r Object
        return R.ok().data("url", url);
    }

}
