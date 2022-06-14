package com.FJ28.serviceedu.service;

import com.FJ28.serviceedu.entity.EduTeacher;

import com.FJ28.serviceedu.entity.vo.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author FJ28
 * @since 2022-06-03
 */
// if there is no IService:
// We need to write a EduService and use write code about how to add a new teacher and so forth;
// but there is a IService: so IService<T> implements all the methods need to write.


public interface EduTeacherService extends IService<EduTeacher> {
    // what if I want to add new services.
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

}
