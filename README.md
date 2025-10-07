
# Personal Task Manager CLI - Enhanced Version
A robust backend system for managing personal tasks with categories, priorities, due dates, and reminders. This application includes advanced features like task sorting, filtering, and statistics.

# Table of Contents
Features
Architecture
Prerequisites
Building the Project
Running the Application
Testing
Exception Handling
Contributing

# Features
~ Task Management: Create, update, delete, and retrieve tasks
~ Categorization: Organize tasks by custom categories
~ Priority Levels: Assign HIGH, MEDIUM, or LOW priority to tasks
~ Status Tracking: Track task status (PENDING, IN_PROGRESS, COMPLETED)
~ Due Date Management: Set and track task due dates
~ Task Filtering: Filter tasks by status, priority, category, or due date
~ Overdue Task Detection: Identify tasks that are past their due date
~ Validation: Comprehensive validation for task data and dates
~ Exception Handling: Custom exceptions for various error scenarios
~ Reporting: Generate task statistics and reports

# Architecture

The application follows a layered architecture pattern with clear separation of concerns:
┌─────────────────────────────────────────────────────────────┐
│                        App (Main)                           │
└───────────────────────────┬─────────────────────────────────┘
                            │
┌───────────────────────────▼─────────────────────────────────┐
│                      Service Layer                          │
│                                                             │
│  ┌─────────────────┐  ┌─────────────┐  ┌────────────────┐   │
│  │  TaskService    │  │CategorySvc  │  │NotificationSvc │   │
│  └────────┬────────┘  └──────┬──────┘  └────────┬───────┘   │
│           │                  │                  │           │
│  ┌────────▼────────┐         │                  │           │
│  │ ReportService   │         │                  │           │
│  └─────────────────┘         │                  │           │
└───────────┬─────────────────┬┴──────────────────┬───────────┘
            │                 │                   │
┌───────────▼─────────────────▼───────────────────▼───────────┐
│                     Repository Layer                         │
│                                                             │
│       ┌─────────────────┐      ┌─────────────────┐          │
│       │ TaskRepository  │      │CategoryRepository│          │
│       └─────────────────┘      └─────────────────┘          │
└───────────────────────────┬─────────────────────────────────┘
                            │
┌───────────────────────────▼─────────────────────────────────┐
│                       Model Layer                           │
│                                                             │
│    ┌─────────┐     ┌──────────┐      ┌─────────────┐        │
│    │  Task   │     │ Category  │      │  Priority   │        │
│    └─────────┘     └──────────┘      └─────────────┘        │
└─────────────────────────────────────────────────────────────┘
                            │
┌───────────────────────────▼─────────────────────────────────┐
│                     Utility Layer                           │
│                                                             │
│    ┌──────────────┐          ┌──────────────────┐           │
│    │DateValidator │          │  TaskValidator   │           │
│    └──────────────┘          └──────────────────┘           │
└─────────────────────────────────────────────────────────────┘

# Key Components:
Model Layer: Contains the core domain objects (Task, Category, Priority)
Repository Layer: Handles data storage and retrieval
Service Layer: Implements business logic and validation rules
Utility Layer: Provides helper functions for validation and data processing
Exception Layer: Custom exceptions for error handling

# Prerequisites
1.Java 17 
2.Maven 3.6 or higher
4.TestNG (testing suite)
3.Git (optional, for cloning the repository)

# Building the Project
To build the project, run the following command from the project root directory:
mvn clean install

*** This command will:
--Clean the target directory
--Compile the source code
--Run the tests
--Package the application into a JAR file

# Testing
  The project uses TestNG for testing. The test suite is comprehensive and covers all critical functionality.
  
# Running All Tests
   mvn test
   
## Quickstart
1. Create a category.
2. Add tasks with title, description, due date, priority, and category.
3. View, update, delete, filter tasks from the menu.
4. Run `mvn test` to verify all business rules and data validation.
5. 
# Usage Examples

// Create a category
Category category = new Category(name, description);

// Create a task
Task task = taskService.createTask
    Priority.HIGH,
    workCategory.getCategoryId();

Updating a Task
// Update task status
taskService.updateTaskStatus(taskId, Status.IN_PROGRESS);

// Update task details
Task updatedTask = new Task();
updatedTask.setTitle("Updated title");
updatedTask.setDescription("Updated description");
updatedTask.setDueDate(LocalDate.parse("2024-01-15"));
updatedTask.setPriority(Priority.MEDIUM);

taskService.updateTask(taskId, updatedTask);

Filtering Tasks
// Get all high priority tasks
List highPriorityTasks = taskService.getTasksByPriority(Priority.HIGH);

// Get all tasks in a specific category
List workTasks = taskService.getTasksByCategory(workCategory.getCategoryId());

// Get all overdue tasks
List overdueTasks = taskService.getOverdueTasks();

Generating Reports
// Get task statistics
TaskStatistics statistics = reportService.generateTaskStatistics();
System.out.println("Total tasks: " + statistics.getTotalTasks());
System.out.println("Completed tasks: " + statistics.getCompletedTasksCount());
System.out.println("Overdue tasks: " + statistics.getOverdueTasksCount());

// Generate productivity report
ProductivityReport report = reportService.generateProductivityReport(
    LocalDate.now().minusDays(7),
    LocalDate.now()
);

# Exception Handling 
The application uses custom exceptions to handle various error scenarios:
1.TaskNotFoundException: When attempting to access a non-existent task
2.InvalidDateException: When providing an invalid date format
3.DuplicateTaskException: When attempting to create a duplicate task
4.TaskValidationException: When task data fails validation rules

---Example of handling exceptions:
try {
    Task task = taskService.getTaskById("non-existent-id");
} catch (TaskNotFoundException e) {
    System.err.println("Error: " + e.getMessage());
    // Handle the error appropriately
}

# Coding Standards
~ Follow Java coding conventions
~ Write unit tests for all new features
~ Update documentation as needed
~ Ensure all tests pass before submitting a pull request

## Troubleshooting
- Ensure you are running with **Java 17+** and have Maven installed.

---
