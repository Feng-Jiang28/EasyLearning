package com.FJ28.serviceedu.controller;

import com.FJ28.commonutils.R;
import com.FJ28.serviceedu.entity.EduTeacher;
import com.FJ28.serviceedu.entity.vo.TeacherQuery;
import com.FJ28.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FJ28
 * @since 2022-06-03
 */
@Api(description = "Teachers Management")
@RestController // return JSON form data:
@RequestMapping("/serviceedu/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("findAll")
    public R list(){
        List<EduTeacher> list = teacherService.list(null);

        return R.ok().data("items", list);
    }

    // This one will be tested by swagger, swagger should be put in parent as a common module

    // logic delete the teacher with the id
    @ApiOperation("Delete according to the the ID")
    @DeleteMapping("{id}")
    public R removeById(
            // the param name will show
            @ApiParam(name = "id", value = "Teacher ID", required = true)
            // if above was not provided the variable name will show
            @PathVariable String id){
        teacherService.removeById(id);
        return R.ok();
    }


    // This is a method of paging:
    // page:
    // limit:
    @GetMapping("/pageTeacher/{page}/{limit}")
    public R pageTeachers(
            @ApiParam(name = "page", value = "Current page", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "Records of Every Page", required = true)
            @PathVariable Long limit){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        teacherService.page(pageParam, null);
        List<EduTeacher> list = pageParam.getRecords();
        long total = pageParam.getTotal();
        // Map map = new HashMap();
        // map.put();
        return R.ok().data("total", total).data("rows", list);
    }

    // How to do a query with extra conditions:
    // Wrapping conditions in an object:
    // The put the object in the api.

    // RequestBody use json to transfer data, and wrap json into object， must use the PostMapping methods.
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery){

        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        // Because we have getters and setters: it is easy to parse the object;
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (level != null) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        teacherService.page(pageTeacher, queryWrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();


        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        teacher.setId(id);
        teacherService.updateById(teacher);
        return R.ok();
    }


}

