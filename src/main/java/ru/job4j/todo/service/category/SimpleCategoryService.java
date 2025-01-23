package ru.job4j.todo.service.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.persistence.category.CategoryStorage;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleCategoryService implements CategoryService {

    private final CategoryStorage storage;

    @Override
    public Collection<Category> findAllCategories() {
        return storage.findAllCategories();
    }

    @Override
    public Optional<Category> findCategoryById(int id) {
        return storage.findCategoryById(id);
    }
}
