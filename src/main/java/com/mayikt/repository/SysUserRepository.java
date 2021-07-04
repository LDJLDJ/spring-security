package com.mayikt.repository;

import com.mayikt.entity.SysPermission;
import com.mayikt.entity.SysUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUser,Integer> {

    SysUser findByUsername(String userName);


}
