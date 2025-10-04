package com.taskmanager.util;

import com.taskmanager.exception.InvalidDateException;
import com.taskmanager.model.Task;
import com.taskmanager.exception.TaskValidationException;
import java.time.LocalDate;

public class TaskValidator {
    public static void validate(Task task) throws TaskValidationException, InvalidDateException {
        if (task.getTitle() == null || task.getTitle().isEmpty())
            throw new TaskValidationException("Title is required.");
        if (task.getDueDate() == null || task.getDueDate().isBefore(LocalDate.now()))
            throw new InvalidDateException("Due date must be today or later.");
    }
}
