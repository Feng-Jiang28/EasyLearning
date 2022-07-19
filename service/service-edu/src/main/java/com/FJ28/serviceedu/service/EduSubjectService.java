package com.FJ28.serviceedu.service;

import com.FJ28.serviceedu.entity.EduSubject;
import com.FJ28.serviceedu.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author FJ28
 * @since 2022-07-17
 */
public interface EduSubjectService extends IService<EduSubject> {

    public void saveSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> nestedList();

}
