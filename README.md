# Personal Task Manager CLI

A robust backend project for managing personal tasks. Features include task creation, filtering, validation, notifications, task statistics, and full test coverage using TestNG.

---

## Features

- Manage tasks: create, update, delete, filter
- Categories and priorities (High, Medium, Low)
- Due date and status tracking (Pending, InProgress, Completed)
- Thread-safe operations; in-memory repository (no DB required)
- Business rule enforcement and custom exceptions
- Reminders for overdue or near-due tasks
- Full validation: duplicates, date formats, empty/edge cases

---

## Technologies

- Java 17
- Maven (project, build & dependency management)
- TestNG (testing suite)

## Testing

Run the TestNG suite:

```
mvn test
```

- Test coverage includes all core business/service methods.
- Exception rules and edge cases are verified.
- Data-driven, grouped, and dependency-based tests are included.
- Automated, reliable validation of all application logic.

---

## Configuration

- The project is self-contained; no configuration files needed.
- All storage is in-memory for simplicity.

---

## Quickstart

1. Create a category.
2. Add tasks with title, description, due date, priority, and category.
3. View, update, delete, filter tasks from the menu.
4. Run `mvn test` to verify all business rules and data validation.

---

## Troubleshooting

- Ensure you are running with **Java 17+** and have Maven installed.

---
