package com.mayikt.entity;

import javax.persistence.*;

@Table(name = "SYS_PERMISSION")
@Entity
public class SysPermission {

    private Integer id;
    private String permName;
    private String permTag;
    private String url;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "perm_name")
    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    @Column(name = "perm_tag")
    public String getPermTag() {
        return permTag;
    }

    public void setPermTag(String permTag) {
        this.permTag = permTag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
