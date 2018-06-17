package com.fxrialab.timetrack.model;

import com.fxrialab.timetrack.security.model.User;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Minh T. on 6/11/2018.
 */
@Entity(name = "tasks")
public class Task extends AbstractPersistable<Long> {
    @ManyToOne(cascade = CascadeType.ALL)
    private Project project;

    @ManyToOne
    private User user;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    private String name;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
}
