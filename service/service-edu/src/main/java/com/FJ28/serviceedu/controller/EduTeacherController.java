package com.FJ28.serviceedu.controller;

import com.FJ28.commonutils.R;
import com.FJ28.serviceedu.entity.EduTeacher;
import com.FJ28.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}

