package com.mayikt.config;

import com.mayikt.entity.SysPermission;
import com.mayikt.entity.SysUser;
import com.mayikt.repository.SysPermissionRepository;
import com.mayikt.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //1. 登录的时候，系统会调用到该方法 userName就是被拦截下来传到这里来的
        SysUser sysUser = sysUserRepository.findByUsername(userName);
        if(sysUser == null){
            return null;
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        //2. 根据userName 找到相应的权限
        sysPermissionRepository.findPermissionByUsername(userName).forEach(i -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(i.getPermTag()));
        });
        sysUser.setAuthorityList(grantedAuthorities);
        return sysUser;
    }
}
