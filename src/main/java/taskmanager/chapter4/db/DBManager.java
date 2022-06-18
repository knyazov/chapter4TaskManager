package taskmanager.chapter4.db;

import org.springframework.stereotype.Service;
import taskmanager.chapter4.entity.Task;

import java.util.ArrayList;

@Service
public class DBManager {

    private Long id = 0L;
    private ArrayList<Task> tasks = new ArrayList<>();

    {
        tasks.add(new Task(++id, "Complete Task 7 Spring Boot", "2020-10-23", "qwerty", true));
        tasks.add(new Task(++id, "Complete Task 6 Spring Boot", "2021-10-23", "qwerty", true));
        tasks.add(new Task(++id, "Complete Task 5 Spring Boot", "2022-10-23", "qwerty", false));
        tasks.add(new Task(++id, "Complete Task 4 Spring Boot", "2023-10-23", "qwerty", false));
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(Long id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void addTask(Task task) {
        task.setId(++id);
        tasks.add(task);
    }

    public void deleteTask(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    public void updateTask(Task task) {
        for (Task t : tasks) {
            if (t.getId().equals(task.getId())) {
                t.setName(task.getName());
                t.setDeadlineDate(task.getDeadlineDate());
                t.setDescription(task.getDescription());
                t.setCompleted(task.isCompleted());
            }
        }
    }
}
