package service;

import model.Todo;

import java.util.List;

public interface ITodoService {

    public Todo addTodo(String title);

    public Todo getTodoById(long todoId);

    public List<Todo> getAllTodos();

    public boolean updateTodo(long todoId);

    public boolean deleteTodo(long todoId);


}
