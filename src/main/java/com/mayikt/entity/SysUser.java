package com.mayikt.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "SYS_USER")
@Entity
public class SysUser implements UserDetails {

    private Integer id;
    private String username;
    private String realname;
    private String password;
    private Date createDate;
    private Date lastLoginTime;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;

    private List<GrantedAuthority> authorityList = new ArrayList<>();

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "last_login_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "account_non_expired")
    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Column(name = "account_non_locked")
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Column(name = "credentials_non_expired")
    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    @Transient
    //账号没有过期
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    @Transient
    //账号没有被锁定
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    @Transient
    //凭证没过期
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    @Transient
    //用户已被禁用
    public boolean isEnabled() {
        return enabled;
    }
    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Transient
    public void setAuthorityList(List<GrantedAuthority> authorityList) {
        this.authorityList = authorityList;
    }
}
