package com.fxrialab.timetrack.model.security;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Minh T. on 6/11/2018.
 */
@Entity(name = "permissions")
public class UserPermission {
    @Id
    private String role_id;
    @Basic
    private String name;
    @Basic
    @Column(length = 1024)
    private String desc;
    @Basic
    @Column(length = 1024)
    private String access;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
