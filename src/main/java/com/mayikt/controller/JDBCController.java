package com.mayikt.controller;

import com.mayikt.entity.SysUser;
import com.mayikt.repository.SysPermissionRepository;
import com.mayikt.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class JDBCController {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    @GetMapping("/showMember")
    public String showMember(){
        System.out.println(sysPermissionRepository.findPermissionByUsername("mayikt_admin"));
        System.out.println(sysUserRepository.findByUsername("mayikt_admin"));
        return "showMember";
    }

    @GetMapping("/addMember")
    public String addMember(){
        return "addMember";
    }

    @GetMapping("/updateMember")
    public String updateMember(){
        return "updateMember";
    }

    @GetMapping("/delMember")
    public String delMember(){
        return "delMember";
    }

}
