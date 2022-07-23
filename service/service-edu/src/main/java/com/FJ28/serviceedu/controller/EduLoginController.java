package com.FJ28.serviceedu.controller;

import com.FJ28.commonutils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // Cors policy
@RestController
@RequestMapping("/serviceedu/user")
public class EduLoginController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("id_token", 1);
    }
}
