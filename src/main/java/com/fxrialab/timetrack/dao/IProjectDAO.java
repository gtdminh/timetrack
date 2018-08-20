package com.fxrialab.timetrack.dao;

import com.fxrialab.timetrack.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Minh T. on 6/11/2018.
 */
@Repository
public interface IProjectDAO extends JpaRepository<Project, Long> {
}
