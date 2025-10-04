package com.taskmanager.service;

import com.taskmanager.model.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.LocalDate;
import java.util.*;


public class ReportServiceTests {

    private ReportService reportService;
    private List<Task> tasks;

    @BeforeMethod
    public void setup() {
        reportService = new ReportService();
        tasks = new ArrayList<>();
        tasks.add(new Task("Badminton", "Play Badminton", LocalDate.now().plusDays(1), Priority.HIGH, "Outdoor"));
        tasks.add(new Task("Laundry", "Wash Clothes", LocalDate.now().plusDays(2), Priority.MEDIUM, "Indoor"));
        tasks.add(new Task("Gardening", "Gardening and Cleaning", LocalDate.now().plusDays(3), Priority.LOW, "Indoor"));
        tasks.get(0).setStatus(Status.COMPLETED);
    }

    @Test(groups = "statistics")
    public void testStatusSummary() {
        Map<Status, Long> summary = reportService.getStatusSummary(tasks);
        Assert.assertTrue(summary.containsKey(Status.COMPLETED));
    }

    @Test(groups = "statistics")
    public void testPrioritySummary() {
        Map<Priority, Long> summary = reportService.getPrioritySummary(tasks);
        Assert.assertEquals(summary.get(Priority.HIGH).longValue(), 1L);
    }
}

