package com.FJ28.serviceedu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

// This class basically stores the query values into an object for query.
@ApiModel(value = "Teacher object for query use", description = "value object")
@Data
public class TeacherQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "name of teacher")
    private String name;

    @ApiModelProperty(value = "level 1 or 2 ")
    private Integer level;

    @ApiModelProperty(value = "start time", example = "2019-01-01 10:10:10")
    private String begin;//

    @ApiModelProperty(value = "end time", example = "2019-12-01 10:10:10")
    private String end;
}
