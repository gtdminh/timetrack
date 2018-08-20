package com.fxrialab.timetrack.model.security;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Minh T. on 6/15/2018.
 */
@Entity(name = "roles")
public class UserRole {
    @Id
    private String role;

    private String desc;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
