package ru.job4j.todo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.task.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

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
    public String getCreationPage() {
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, Model model) {
        var savedTask = taskService.save(task);
        if (savedTask.isEmpty()) {
            model.addAttribute("message",
                    "Не удалось создать задание попробуйте заново");
            return "errors/404";
        }
        return "redirect:/tasks/all";
    }

    @GetMapping("/{id}")
    public String getTaskByDescription(Model model, @PathVariable int id) {
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
            model.addAttribute("message","Не удалось удалить задание");
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
        return "tasks/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model) {
        var taskIsUpdated = taskService.update(task.getId(), task);
        if (!taskIsUpdated) {
            model.addAttribute("message",
                    "Не получилось отредактировать задание");
            return "errors/404";
        }
        return "redirect:/tasks/all";
    }

    @GetMapping("/settaskasdone/{id}")
    public String setTaskAsDone(Model model, @PathVariable int id) {
        var taskIsSetAsDone = taskService.setTaskAsDone(id);
        if (!taskIsSetAsDone) {
            model.addAttribute("message", "Не удалось сменить статус задания");
            return "errors/404";
        }
        return "redirect:/tasks/completed";
    }
}




