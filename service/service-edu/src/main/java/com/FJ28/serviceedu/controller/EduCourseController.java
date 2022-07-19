package com.FJ28.serviceedu.controller;


import com.FJ28.commonutils.R;
import com.FJ28.serviceedu.entity.vo.CourseInfoVo;
import com.FJ28.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author FJ28
 * @since 2022-07-18
 */
@Api(description = "Course management")
@CrossOrigin
@RestController
@RequestMapping("/serviceedu/edu-course")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "Adding a New Course")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(
            @ApiParam(name = "CourseInfoVo", value = "Basic info", required = true)
            @RequestBody CourseInfoVo courseInfoVo){
        String courseId = courseService.saveCourseInfo(courseInfoVo);
        if(!StringUtils.isEmpty(courseId)){
            return R.ok().data("courseId", courseId);
        }
        return R.ok().message("Save Failure");
    }

}

