package com.taskmanager.repository;

import com.taskmanager.model.Category;
import java.util.*;

public class CategoryRepository {
    private final Map<String, Category> categories = new HashMap<>();

    public void save(Category category) { categories.put(category.getCategoryId(), category); }
    public Category findById(String categoryId) { return categories.get(categoryId); }
    public List<Category> findAll() { return new ArrayList<>(categories.values()); }
}
