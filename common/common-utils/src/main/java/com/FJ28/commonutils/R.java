package com.FJ28.commonutils;


// Class for common result used to create json object

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {

    @ApiModelProperty(value = "SUCCESS OR NOT")
    private Boolean success;

    @ApiModelProperty(value = "State code")
    private Integer code;

    @ApiModelProperty(value = "Response Message")
    private String message;

    @ApiModelProperty(value = "Response Data")
    private Map<String, Object> data = new HashMap<String, Object>();

    // private because we need want it to be created by other class  other than the provided methods.
    private R(){}

    //
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("SUCCESS");
        return r;
    }

    //
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("FAILURE");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public R message(String message){
        this.setMessage(message);
        return this;
    }
    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
