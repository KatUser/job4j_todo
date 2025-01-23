package ru.job4j.todo.persistence.category;

import ru.job4j.todo.model.Category;

import java.util.Collection;
import java.util.Optional;

public interface CategoryStorage {
    Collection<Category> findAllCategories();
    Optional<Category> findCategoryById(int id);
}
