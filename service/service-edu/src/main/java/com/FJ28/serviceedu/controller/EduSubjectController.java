package com.FJ28.serviceedu.controller;


import com.FJ28.commonutils.R;
import com.FJ28.serviceedu.entity.EduSubject;
import com.FJ28.serviceedu.entity.subject.OneSubject;
import com.FJ28.serviceedu.service.EduSubjectService;
import org.apache.commons.logging.impl.LogKitLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author FJ28
 * @since 2022-07-17
 */
@RestController
@RequestMapping("/serviceedu/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    // 添加课程分类
    // 获取上传过来的文件， 把文件读取出来
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file, EduSubjectService subjectService){

        // 上传过来excel文件
        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> subjectNestedVoList = subjectService.nestedList();
        return R.ok().data("items", subjectNestedVoList);
    }
}

