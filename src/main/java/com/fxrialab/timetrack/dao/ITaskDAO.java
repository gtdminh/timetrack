package com.fxrialab.timetrack.dao;

import com.fxrialab.timetrack.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Minh T. on 6/11/2018.
 */
@Repository
public interface ITaskDAO extends JpaRepository<Task, Long> {
}
