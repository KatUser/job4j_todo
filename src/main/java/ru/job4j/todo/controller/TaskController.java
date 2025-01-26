package ru.job4j.todo.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.category.CategoryService;
import ru.job4j.todo.service.priority.PriorityService;
import ru.job4j.todo.service.task.TaskService;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    private final PriorityService priorityService;

    private final CategoryService categoryService;

    @GetMapping
    public String getAllTasksInLists() {
        return "tasks/list";
    }

    @GetMapping("/all")
    public String getAllTasks(Model model) {
        model.addAttribute("tasks",
                taskService.findAllTasks());
        return "tasks/alltasks";
    }

    @GetMapping("/completed")
    public String getCompletedTasks(Model model) {
        model.addAttribute("tasks", taskService.findCompletedTasks());
        return "tasks/alltasks";
    }

    @GetMapping("/new")
    public String getNewTasks(Model model) {
        model.addAttribute("tasks", taskService.findNewTasks());
        return "tasks/alltasks";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("priorities", priorityService.findAllPriorities());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task,
                         @SessionAttribute User user,
                         @RequestParam List<Integer> categoriesId,
                         Model model) {
        task.setUser(user);
        List<Category> categories = new ArrayList<>();
        categoriesId.forEach(id -> categoryService.findCategoryById(id).ifPresent(categories::add));
        task.setCategoriesList(categories);
        var savedTask = taskService.create(task);
        if (savedTask.isEmpty()) {
            model.addAttribute("message",
                    "Не удалось создать задание попробуйте заново");
            return "errors/404";
        }
         return "redirect:/tasks/all";
    }

    @GetMapping("/{id}")
    public String getTaskById(Model model, @PathVariable int id) {
        var taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Такого задания нет");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/one";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        var taskIsDeleted = taskService.deleteById(id);
        if (!taskIsDeleted) {
            model.addAttribute("message", "Не удалось удалить задание");
            return "errors/404";
        }
        return "redirect:/tasks/all";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(Model model, @PathVariable int id) {
        var taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Такого задания нет");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        model.addAttribute("priorities", priorityService.findAllPriorities());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "tasks/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task,
                         @RequestParam List<Integer> categoriesId,
                         @SessionAttribute User user,
                         Model model) {
        int taskId = task.getId();
        task.setUser(user);
        task.setDone(taskService.findById(taskId).get().isDone());
        List<Category> categories = new ArrayList<>();
        categoriesId.forEach(id -> categoryService.findCategoryById(id).ifPresent(categories::add));
        task.setCategoriesList(categories);
        var savedTask = taskService.create(task);
        if (savedTask.isEmpty()) {
            model.addAttribute("message",
                    "Не удалось создать задание попробуйте заново");
            return "errors/404";
        }
        return "redirect:/tasks/all";
    }

    @GetMapping("/done/{id}")
    public String setTaskAsDone(Model model, @PathVariable int id) {
        var taskIsSetAsDone = taskService.setTaskAsDone(id);
        if (!taskIsSetAsDone) {
            model.addAttribute("message", "Не удалось сменить статус задания");
            return "errors/404";
        }
        return "redirect:/tasks/completed";
    }
}




