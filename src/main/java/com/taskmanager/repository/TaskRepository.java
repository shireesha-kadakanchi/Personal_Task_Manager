package com.taskmanager.repository;

import com.taskmanager.model.Task;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TaskRepository {
    private final Map<String, Task> tasks = new ConcurrentHashMap<>();

    public void save(Task task) { tasks.put(task.getTaskId(), task); }
    public Task findById(String taskId) { return tasks.get(taskId); }
    public List<Task> findAll() { return new ArrayList<>(tasks.values()); }
    public void delete(String taskId) { tasks.remove(taskId); }
}
