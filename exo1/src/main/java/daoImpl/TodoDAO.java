package daoImpl;

import dao.BaseDAO;
import model.Todo;

import javax.persistence.*;

import java.util.List;

public class TodoDAO extends BaseDAO<Todo> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("exo1_jpa");
    private static EntityManager em = emf.createEntityManager();
    private static EntityTransaction transac = em.getTransaction();

    @Override
    public Todo get(long id) {
        transac.begin();

        Todo todo = em.find(Todo.class,id);

        System.out.println(todo);
        em.close();
        emf.close();
        return todo;
    }

    @Override
    public List<Todo> getAll() {
        List<Todo> todoList = null;
        todoList = em.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();

        for (Todo t : todoList) {
            System.out.println(t);
        }
        em.close();
        emf.close();
        return todoList;
    }

    @Override
    public void add(Todo element) {
        transac.begin();

        Todo todo = new Todo();
        em.persist(todo);
        System.out.println("todo n° " + todo.getId() + "créée");
        transac.commit();
        em.close();
        emf.close();
    }

    @Override
    public boolean update(Todo updatedTodo) {
        transac.begin();

        Todo existingTodo = this.get(updatedTodo.getId());

        if (existingTodo != null) {
            existingTodo.setTitle(updatedTodo.getTitle());
            existingTodo.setStatus(updatedTodo.getStatus());

            em.merge(existingTodo);

            System.out.println("Todo n° " + existingTodo.getId() + " mise à jour avec succés");
            transac.commit();
            em.close();
            emf.close();
            return true;
        } else {
            System.out.println("Todo non trouvée");
            em.close();
            emf.close();
            return false;
        }

    }

    @Override
    public boolean delete(Todo element) {
        transac.begin();

        Todo todo = this.get(element.getId());

        if (todo != null) {
            em.remove(todo);

            System.out.println("Todo n° " + todo.getId() + " supprimée avec succés");
            transac.commit();
            em.close();
            emf.close();
            return true;
        } else {
            System.out.println("Todo non trouvée");
            transac.commit();
            em.close();
            emf.close();
            return false;
        }
    }


}
