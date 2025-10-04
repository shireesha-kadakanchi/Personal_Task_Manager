package com.taskmanager.service;

import com.taskmanager.model.*;
import com.taskmanager.exception.*;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.util.TaskValidator;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repo) { this.repository = repo; }

    public Task createTask(Task task) throws DuplicateTaskException, InvalidDateException, TaskValidationException {
        validateTask(task);
        for (Task existing : repository.findAll()) {
            if (existing.getTitle().equalsIgnoreCase(task.getTitle())
                    && existing.getDueDate().equals(task.getDueDate())) {
                throw new DuplicateTaskException("Duplicate task detected.");
            }
        }
        repository.save(task);
        return task;
    }
    public void updateTask(String taskId, Task updatedTask) throws TaskNotFoundException, TaskValidationException, InvalidDateException {
        Task task = repository.findById(taskId);
        if (task == null) throw new TaskNotFoundException("Task not found.");
        validateTask(updatedTask);
        updatedTask.setLastModifiedDate(java.time.LocalDateTime.now());
        repository.save(updatedTask);
    }
    public void deleteTask(String taskId) throws TaskNotFoundException {
        if (repository.findById(taskId) == null)
            throw new TaskNotFoundException("Task not found.");
        repository.delete(taskId);
    }
    public Task getTaskById(String taskId) throws TaskNotFoundException {
        Task task = repository.findById(taskId);
        if (task == null)
            throw new TaskNotFoundException("Task not found.");
        return task;
    }
    public List<Task> getTasksByStatus(Status status) {
        return repository.findAll().stream().filter(t -> t.getStatus() == status).collect(Collectors.toList());
    }
    public List<Task> getTasksByPriority(Priority priority) {
        return repository.findAll().stream().filter(t -> t.getPriority() == priority).collect(Collectors.toList());
    }
    public List<Task> getTasksByCategory(String categoryId) {
        return repository.findAll().stream().filter(t -> categoryId.equals(t.getCategoryId())).collect(Collectors.toList());
    }
    public List<Task> getOverdueTasks() {
        return repository.findAll().stream().filter(t -> t.getDueDate() != null && t.getDueDate().isBefore(java.time.LocalDate.now())).collect(Collectors.toList());
    }
    private void validateTask(Task task) throws TaskValidationException, InvalidDateException {
        TaskValidator.validate(task);
    }
}
