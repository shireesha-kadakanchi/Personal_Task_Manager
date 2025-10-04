package com.taskmanager.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Task {
    private final String taskId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private String categoryId;
    private Status status;
    private final LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public Task(String title, String description, LocalDate dueDate, Priority priority, String categoryId) {
        this.taskId = UUID.randomUUID().toString();
        this.setTitle(title);
        this.setDescription(description);
        this.setDueDate(dueDate);
        this.setPriority(priority);
        this.setCategoryId(categoryId);
        this.setStatus(Status.PENDING);
        this.createdDate = LocalDateTime.now();
        this.setLastModifiedDate(LocalDateTime.now());
    }

	public String getTaskId() {
		return taskId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
