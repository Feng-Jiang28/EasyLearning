package com.FJ28.serviceedu.service.impl;

import com.FJ28.servicebase.exceptionhandler.FJ28Exception;
import com.FJ28.serviceedu.entity.EduCourse;
import com.FJ28.serviceedu.entity.EduCourseDescription;
import com.FJ28.serviceedu.entity.vo.CourseInfoVo;
import com.FJ28.serviceedu.mapper.EduCourseMapper;
import com.FJ28.serviceedu.service.EduCourseDescriptionService;
import com.FJ28.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author FJ28
 * @since 2022-07-18
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    // adding course info here:
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        // 1. Adding to course table.
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        // baseMapper
        int insert = baseMapper.insert(eduCourse);

        if(insert <= 0){
            throw new FJ28Exception(20001, "添加课程信息失败");
        }

        // Bonding course description with course itself
        String cid = eduCourse.getId();

        // 2. 向课程简介表中加数据
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());

        courseDescription.setId(cid);
        boolean result = courseDescriptionService.save(courseDescription);
        if(!result){
            throw new FJ28Exception(20001, "Course info saving failure");
        }
        return courseDescription.getId();
    }
}
