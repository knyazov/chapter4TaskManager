package taskmanager.chapter4.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import taskmanager.chapter4.db.DBManager;
import taskmanager.chapter4.entity.Task;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final DBManager db;

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("tasks", db.getTasks());
        return "index";
    }

    @PostMapping(value = "/addTask")
    public String addTask(@RequestParam(name = "tName", defaultValue = "") String name,
                          @RequestParam(name = "tDescription", defaultValue = "") String description,
                          @RequestParam(name = "tDeadlineDate", defaultValue = "") String deadlineDate) {
        Task task = new Task(null, name, deadlineDate, description, false);
        db.addTask(task);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable Long id,
                          Model model) {
        model.addAttribute("task", db.getTask(id));
        return "details";
    }

    @PostMapping(value = "/details/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam(name = "tName", defaultValue = "") String name,
                       @RequestParam(name = "tDescription", defaultValue = "") String description,
                       @RequestParam(name = "tDeadlineDate", defaultValue = "") String deadlineDate,
                       @RequestParam(name = "isCompleted", defaultValue = "") boolean isCompleted) {
        Task task = new Task(id, name, deadlineDate, description, isCompleted);
        db.updateTask(task);
        return "redirect:/";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id) {
        db.deleteTask(id);
        return "redirect:/";
    }
}
