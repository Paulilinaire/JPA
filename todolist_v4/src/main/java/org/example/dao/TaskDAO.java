package org.example.dao;

import org.example.model.Task;

import java.util.List;

public interface TaskDAO {

    public boolean addTask(Task task, long id);

    public List<Task> getAllTasks();

    public boolean deleteTask(Long taskId);

    public boolean markTaskAsCompleted(Long taskId);

    public List<Task> getTasksByPersonId(Long personId);

    public List<Task> getTasksByCategoryId(Long categoryId);

    public void addTaskToCategory(Long taskId, Long categoryId);

    public void removeTaskFromCategory(Long taskId, Long categoryId);
}
