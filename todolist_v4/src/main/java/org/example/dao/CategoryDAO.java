package org.example.dao;

import org.example.model.Category;
import org.example.model.Person;

import java.util.List;

public interface CategoryDAO {
    public void addCategory(Category category);

    public void deleteCategory(Long id);

    public Category findCategoryById(Long categoryId);
}
