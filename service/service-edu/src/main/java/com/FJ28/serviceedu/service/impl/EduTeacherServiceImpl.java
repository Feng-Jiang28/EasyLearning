package com.FJ28.serviceedu.service.impl;

import com.FJ28.serviceedu.entity.EduTeacher;
import com.FJ28.serviceedu.entity.vo.TeacherQuery;
import com.FJ28.serviceedu.mapper.EduTeacherMapper;
import com.FJ28.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

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
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery)
    {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if (teacherQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (level != null ) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }

}
