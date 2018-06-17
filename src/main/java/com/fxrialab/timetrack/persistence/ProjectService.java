package com.fxrialab.timetrack.persistence;

import com.fxrialab.timetrack.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Minh T. on 6/11/2018.
 */
public interface ProjectService {
    public Project get(Long project_id);
    public List<Project> list(Long user_id, Pageable page);
    public Project save(Project project);
    public void remove(Long project_id);
    public void archive(Long project_id);
}
