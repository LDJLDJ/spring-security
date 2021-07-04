package com.mayikt.repository;

import com.mayikt.entity.SysPermission;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SysPermissionRepository extends JpaRepository<SysPermission, Integer> {

    List<SysPermission> findAll();

    @Query(value = "select sp.* from sys_user su " +
            "left join sys_user_role sur on su.id = sur.user_id " +
            "left join sys_role_permission srp on sur.role_id = srp.role_id " +
            "left join sys_permission sp on srp.perm_id = sp.id " +
            "where su.username=?1",nativeQuery = true)
    List<SysPermission> findPermissionByUsername(String userName);
}
