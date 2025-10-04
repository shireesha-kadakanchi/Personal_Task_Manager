package com.taskmanager.service;

import com.taskmanager.model.Category;
import com.taskmanager.repository.CategoryRepository;
import com.taskmanager.exception.TaskValidationException;

import java.util.List;

public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repo) {
        this.repository = repo;
    }

    public Category createCategory(String name, String description) throws TaskValidationException {
        if (name == null || name.trim().isEmpty())
            throw new TaskValidationException("Category name is required.");
        Category category = new Category(name, description);
        repository.save(category);
        return category;
    }

    public Category getCategoryById(String id) {
        return repository.findById(id);
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public void updateCategory(String id, String name, String description) throws TaskValidationException {
        Category category = repository.findById(id);
        if (category == null)
            throw new TaskValidationException("Category not found.");
        if (name != null && !name.trim().isEmpty()) category.setName(name);
        if (description != null) category.setDescription(description);
        repository.save(category);
    }

    public void deleteCategory(String id) throws TaskValidationException {
        Category category = repository.findById(id);
        if (category == null)
            throw new TaskValidationException("Category not found.");
        repository.findAll().remove(category);
    }
}
