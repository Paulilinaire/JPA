package demo;

import entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Demo1 {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");

    public static void create() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Person person = new Person("Jean-michel", "Voixdechiotte");
        em.persist(person);
        System.out.println("person n° " + person.getId() + "créée");
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static void find() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Person person = em.find(Person.class,3L);

        System.out.println(person);
        em.close();
        emf.close();
    }

    public static void remove() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Person person = em.find(Person.class,2L);
        em.remove(person);
        System.out.println("Suppression effectuée");

        em.getTransaction().commit(); // commit nécessaire car on modifie la BDD
        em.close();
        emf.close();
    }

    public static void createQuery() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Person person = new Person("Jean-michel", "Amoitié");
        Person person1 = new Person("Jean-michel", "Alouette");
        em.persist(person);
        em.persist(person1);

        List<Person> personList = null;
        personList = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();

        for (Person p : personList) {
            System.out.println(p);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }



}
