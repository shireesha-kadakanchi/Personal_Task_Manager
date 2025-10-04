package com.taskmanager.service;

import com.taskmanager.model.Task;
import java.time.LocalDate;
import java.util.List;

public class NotificationService {

    public void sendDeadlineReminders(List<Task> tasks) {
        LocalDate today = LocalDate.now();
        for (Task task : tasks) {
            if (task.getDueDate() != null &&
                    (task.getDueDate().isEqual(today) || task.getDueDate().isBefore(today)) &&
                    task.getStatus() != com.taskmanager.model.Status.COMPLETED) {
                System.out.println("Reminder: Task '" +
                        task.getTitle() + "' is due or overdue (" +
                        task.getDueDate() + ")");
            }
        }
    }

    public void sendUpcomingReminders(List<Task> tasks, int daysAhead) {
        LocalDate threshold = LocalDate.now().plusDays(daysAhead);
        for (Task task : tasks) {
            if (task.getDueDate() != null &&
                    task.getDueDate().isAfter(LocalDate.now()) &&
                    task.getDueDate().isBefore(threshold) &&
                    task.getStatus() != com.taskmanager.model.Status.COMPLETED) {
                System.out.println("Upcoming: Task '" +
                        task.getTitle() + "' is due on " +
                        task.getDueDate());
            }
        }
    }
}
