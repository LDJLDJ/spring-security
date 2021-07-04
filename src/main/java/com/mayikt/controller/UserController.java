package com.mayikt.controller;

import com.mayikt.entity.SysPermission;
import com.mayikt.repository.SysPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUserName")
    @ResponseBody
    public String getUserName(){
        return "罗密欧";
    }

    @GetMapping("/getUserSex")
    @ResponseBody
    public String getUserSex(){
        return "男";
    }

    @GetMapping("/getUserAge")
    @ResponseBody
    public String getUserAge(){
        return "18";
    }

    @GetMapping("/loginPage")
    public String userLogin(){
        return "login";
    }
}
