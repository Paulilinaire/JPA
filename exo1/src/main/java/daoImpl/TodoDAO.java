package daoImpl;

import dao.BaseDAO;
import model.Todo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class TodoDAO extends BaseDAO<Todo> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("exo1_jpa");
    private static EntityManager em = emf.createEntityManager();
    private static EntityTransaction transac = em.getTransaction();


    @Override
    public boolean add(Todo element) {
        transac.begin();

        Todo todo = new Todo();
        em.persist(todo);
        System.out.println("person n° " + todo.getId() + "créée");
        transac.commit();
        em.close();
        emf.close();
        return true;
    }

    @Override
    public boolean update(Todo element) {
        return false;
    }

    @Override
    public boolean delete(Todo element) {
        transac.begin();

        Todo todo = em.find(Todo.class,2L);
        em.remove(todo);
        System.out.println("Suppression effectuée");

        transac.commit();
        em.close();
        emf.close();
        return true;
    }

    @Override
    public Todo get(long id) {
        return null;
    }

    @Override
    public List<Todo> getAll() {
        return null;
    }
}
