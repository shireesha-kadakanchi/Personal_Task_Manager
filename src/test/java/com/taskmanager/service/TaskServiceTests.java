package com.taskmanager.service;

import com.taskmanager.exception.*;
import com.taskmanager.model.*;
import com.taskmanager.repository.TaskRepository;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.util.List;


public class TaskServiceTests {

    private TaskService taskService;
    private TaskRepository repository;
    private Category testCategory;
    private Category testCategory2;
    List<Task> tasks;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        repository = new TaskRepository();
        taskService = new TaskService(repository);
        testCategory = new Category("Indoor", "Indoor Activities");
        testCategory2 = new Category("Outdoor", "Outdoor Activities");
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        repository.findAll().clear();
    }

    @DataProvider(name = "dateProvider")
    public Object[][] dateProvider() {
        return new Object[][]{
                {LocalDate.now().plusDays(1), false},
                {LocalDate.now(), false},
                {LocalDate.now().minusDays(1), true},
        };
    }

    @Test(groups = {"validation"}, dataProvider = "dateProvider")
    public void testDueDateValidation(LocalDate dueDate, boolean expectException) {
        Task task = new Task("Shopping", "Outdoor Shopping", dueDate, Priority.HIGH, testCategory2.getCategoryId());
        if (expectException) {
            Assert.expectThrows(InvalidDateException.class, () -> taskService.createTask(task));
        } else {
            try {
                Task created = taskService.createTask(task);
                Assert.assertEquals(created.getDueDate(), dueDate);
            } catch (Exception e) {
                Assert.fail("Failed with exception: " + e.getMessage());
            }
        }
    }

    @DataProvider(name = "priorityProvider")
    public Object[][] priorityProvider() {
        return new Object[][] {
                {"Badminton","Play Badminton",LocalDate.now().plusDays(1), Priority.HIGH},
                {"Laundry", "Washing Clothes",LocalDate.now().plusDays(2), Priority.MEDIUM},
                {"Gardening","Gardening and Cleaning",LocalDate.now().plusDays(3), Priority.LOW}
        };
    }

    @Test(groups = {"taskOperations"},dataProvider = "priorityProvider")
    public void testCreateTaskWithDifferentPriorities(String taskName, String taskDesc, LocalDate dueDate, Priority priority) throws Exception {
        Task task = new Task(taskName,taskDesc,dueDate,priority, testCategory.getCategoryId());
        Task created = taskService.createTask(task);
        Assert.assertNotNull(created.getTaskId());
        Assert.assertEquals(created.getPriority(), priority);
    }

    @Test(groups = {"validation"}, expectedExceptions = DuplicateTaskException.class)
    public void testDuplicateTaskCreationThrows() throws Exception {
        Task task1 = new Task("Gardening", "Gardening and Cleaning", LocalDate.now().plusDays(10), Priority.MEDIUM, testCategory.getCategoryId());
        Task task2 = new Task("Gardening", "Gardening and Cleaning", LocalDate.now().plusDays(10), Priority.MEDIUM, testCategory.getCategoryId());
        taskService.createTask(task1);
        taskService.createTask(task2);
    }

    @Test(groups = {"validation"}, expectedExceptions = TaskValidationException.class)
    public void testCreateTaskWithEmptyTitleThrows() throws Exception {
        Task task = new Task("", "Grocery Shopping", LocalDate.now().plusDays(5), Priority.LOW, testCategory.getCategoryId());
        taskService.createTask(task);
    }

    @Test(groups = {"exception"}, expectedExceptions = TaskNotFoundException.class)
    public void testUpdateNonExistingTaskThrows() throws Exception {
        Task nonExistingTask = new Task("Trip", "Going to Trip", LocalDate.now().plusDays(5), Priority.HIGH, testCategory.getCategoryId());
        taskService.updateTask("001", nonExistingTask);
    }

    @Test(groups = {"filtering", "stateBased"}, dependsOnMethods = "testCreateTaskWithDifferentPriorities")
    public void testGetTasksByStatusReturnsCorrectList() throws Exception {
        Task t1 = new Task("Maintenance", "Home Maintenance", LocalDate.now().plusDays(1), Priority.HIGH, testCategory2.getCategoryId());
        t1.setStatus(Status.PENDING);
        Task t2 = new Task("Doctor Appointment", "Health checkup", LocalDate.now().plusDays(2), Priority.MEDIUM, testCategory.getCategoryId());
        t2.setStatus(Status.COMPLETED);
        Task t3 = new Task("Attending Marriage", "Marriage Event", LocalDate.now().plusDays(3), Priority.LOW, testCategory.getCategoryId());
        t3.setStatus(Status.INPROGRESS);


        taskService.createTask(t1);
        taskService.createTask(t2);
        taskService.createTask(t3);

        List<Task> pendingTasks = taskService.getTasksByStatus(Status.PENDING);
        Assert.assertTrue(pendingTasks.stream().allMatch(t -> t.getStatus() == Status.PENDING));
        Assert.assertEquals(pendingTasks.size(), 7);

        List<Task> completedTasks = taskService.getTasksByStatus(Status.COMPLETED);
        Assert.assertTrue(completedTasks.stream().allMatch(t -> t.getStatus() == Status.COMPLETED));
        Assert.assertEquals(completedTasks.size(), 1);
    }

    // Test sequence for creating then updating a task
    @Test(groups = {"taskOperations"}, priority = 1)
    public void testCreateTask() throws Exception {
        Task task = new Task("Reception Party", "Attending Party", LocalDate.now().plusDays(1), Priority.HIGH, testCategory2.getCategoryId());
        Task created = taskService.createTask(task);
        Assert.assertNotNull(created.getTaskId());
    }

    @Test(groups = {"taskOperations"}, dependsOnMethods = "testCreateTask", priority = 2)
    public void testUpdateTask() throws Exception {
        List<Task> allTasks = taskService.getTasksByStatus(Status.PENDING);
        Assert.assertFalse(allTasks.isEmpty());
        Task task = allTasks.get(0);
        task.setStatus(Status.COMPLETED);
        taskService.updateTask(task.getTaskId(), task);
        Task updated = taskService.getTaskById(task.getTaskId());
        Assert.assertEquals(updated.getStatus(), Status.COMPLETED);
    }

    @Test(groups = {"taskOperations", "stateBased"}, dependsOnMethods = "testUpdateTask", priority = 3)
    public void testDeleteTaskAndEnsureRemoval() throws Exception {
        List<Task> allTasks = taskService.getTasksByStatus(Status.COMPLETED);
        Assert.assertFalse(allTasks.isEmpty());
        Task task = allTasks.get(0);
        taskService.deleteTask(task.getTaskId());
        Assert.expectThrows(TaskNotFoundException.class, () -> taskService.getTaskById(task.getTaskId()));
    }

    @Test(groups = {"edgeCases"})
    public void testTaskWithLargeDescription() throws Exception {
        String longDesc = "GroceryShopping".repeat(1000);
        Task t = new Task("Shopping", longDesc, LocalDate.now().plusDays(10), Priority.LOW, testCategory.getCategoryId());
        Task created = taskService.createTask(t);
        Assert.assertEquals(created.getDescription().length(), 15000);
    }
}
