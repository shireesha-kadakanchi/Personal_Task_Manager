package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.model.Priority;
import com.taskmanager.model.Status;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {

    public Map<Status, Long> getStatusSummary(List<Task> tasks) {
        return tasks.stream().collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
    }

    public Map<Priority, Long> getPrioritySummary(List<Task> tasks) {
        return tasks.stream().collect(Collectors.groupingBy(Task::getPriority, Collectors.counting()));
    }

    public long countOverdueTasks(List<Task> tasks) {
        return tasks.stream()
                .filter(t -> t.getDueDate() != null && t.getDueDate().isBefore(java.time.LocalDate.now()) && t.getStatus() != Status.COMPLETED)
                .count();
    }

    public void printStatistics(List<Task> tasks) {
        System.out.println("--- Task Statistics ---");
        System.out.println("Total tasks: " + tasks.size());
        System.out.println("By Status: " + getStatusSummary(tasks));
        System.out.println("By Priority: " + getPrioritySummary(tasks));
        System.out.println("Overdue tasks: " + countOverdueTasks(tasks));
    }
}
