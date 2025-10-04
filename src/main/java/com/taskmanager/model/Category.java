package com.taskmanager.model;

import java.util.UUID;

public class Category {
    private final String categoryId;
    private String name;
    private String description;

    public Category(String name, String description) {
        this.categoryId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    public String getCategoryId() { return categoryId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
}
