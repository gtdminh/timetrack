package com.fxrialab.timetrack.model;

import com.fxrialab.timetrack.model.security.User;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Minh T. on 6/11/2018.
 */
@Entity(name="projects")
public class Project extends AbstractPersistable<Long> {
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private ProjectDetail detail;

    @Basic
    @Column(nullable = false)
    private String name;

    @Basic
    @Column(length = 1024)
    private String desc;

    @Basic
    private String kind;

    @Basic
    @Column(length = 1024)
    private String notes;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProjectDetail getDetail() {
        return detail;
    }

    public void setDetail(ProjectDetail detail) {
        this.detail = detail;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }
}
