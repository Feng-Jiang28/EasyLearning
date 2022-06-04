package com.FJ28.commonutils;

// This module is basically to define the uniform
// json structure

/*
*
*
{
    "success": bool
    "code": Integer, // response code
    "message": String , // response message
    "data": HashMap // response data.
}
*
*
* */

// This interface is used to define state code/

// enum can also be used to define the state code.
public interface ResultCode {

    public static Integer SUCCESS = 20000;
    public static Integer ERROR = 20001;
}
