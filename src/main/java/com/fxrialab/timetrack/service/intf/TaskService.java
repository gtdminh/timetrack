package com.fxrialab.timetrack.service.intf;

import com.fxrialab.timetrack.model.Task;

import java.util.List;

/**
 * Created by Minh T. on 6/11/2018.
 */
public interface TaskService {
    List<Task> listByProject(Long project_id);
    public List<Task> listByUser(Long user_id);
    public Task save(Task task);
    public void remove(Long task_id);

}
