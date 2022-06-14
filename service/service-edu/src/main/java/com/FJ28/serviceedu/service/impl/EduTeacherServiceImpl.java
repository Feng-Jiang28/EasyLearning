package com.FJ28.serviceedu.service.impl;

import com.FJ28.serviceedu.entity.EduTeacher;
import com.FJ28.serviceedu.entity.vo.TeacherQuery;
import com.FJ28.serviceedu.mapper.EduTeacherMapper;
import com.FJ28.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author FJ28
 * @since 2022-06-03
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {

    }
}
