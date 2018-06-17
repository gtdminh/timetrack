package com.fxrialab.timetrack.model;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by Minh T. on 6/11/2018.
 */
@Entity(name = "project_details")
public class ProjectDetail extends AbstractPersistable<Long>{
    @Basic
    @Column(nullable = false)
    private String kind;

    @OneToOne
    private Project project;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
