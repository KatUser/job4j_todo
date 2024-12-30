package ru.job4j.todo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.interfaces.TaskService;

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

    @GetMapping("/alltasks")
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTasks());
        return "tasks/alltasks";
    }

    @GetMapping("/completedtasks")
    public String getCompletedTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTasks());
        return "tasks/completedtasks";
    }

    @GetMapping("/newtasks")
    public String getNewTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTasks());
        return "tasks/newtasks";
    }

    @GetMapping("/create")
    public String getCreationPage() {
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task,
                         Model model) {
        try {
            taskService.save(task);
            return "redirect:/tasks";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/{id}")
    public String getTaskByDescription(Model model,
                                       @PathVariable int id) {
        var taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Такого задания нет");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/one";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
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
    public String update(@ModelAttribute Task task,
                         Model model) {
        var modelAttTask = ((Task) model.getAttribute("task"));
        taskService.update(task.getId(), modelAttTask);
        return "redirect:/tasks/alltasks";
    }

    @GetMapping("/settaskasdone/{id}")
    public String setTaskAsDone(Model model, @PathVariable int id) {
        var taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Такого задания нет");
            return "errors/404";
        }
        taskService.setTaskAsDone(id);
        return "redirect:/tasks/completedtasks";
    }
}




