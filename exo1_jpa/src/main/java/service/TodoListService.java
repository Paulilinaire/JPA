package service;

import daoImpl.TodoDAO;
import model.Todo;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

public class TodoListService implements ITodoService {

    private TodoDAO todoDAO;
    public TodoListService() {
        todoDAO = new TodoDAO() ;
    }

    @Override
    public Todo addTodo(String title, boolean status) throws PersistenceException {
        Todo todo = new Todo(title, status);
        todoDAO.add(todo);
        return todo;
    }

    @Override
    public Todo getTodoById(long todoId) throws PersistenceException{
        try {
            return todoDAO.get(todoId);

        }catch (PersistenceException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Todo> getAllTodos() throws PersistenceException{
        try {
            return todoDAO.getAll();
        }catch (PersistenceException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateTodo(long todoId) throws PersistenceException{
        return false;
    }

    @Override
    public boolean deleteTodo(long todoId) throws PersistenceException{
        return false;
    }
}
