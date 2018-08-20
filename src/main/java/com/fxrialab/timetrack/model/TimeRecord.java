package com.fxrialab.timetrack.model;

import com.fxrialab.timetrack.model.security.User;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Minh T. on 6/11/2018.
 */
@Entity(name="time_records")
public class TimeRecord extends AbstractPersistable<Long> {
    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Project project;

    @ManyToOne
    private Task task;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromTime;

    @Column
    private int duration;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
