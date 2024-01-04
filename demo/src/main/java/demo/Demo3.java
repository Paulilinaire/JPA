package demo;

import entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Demo3 {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");

    public static void create() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person person = new Person("Amélie","Laout");
        em.persist(person);
        System.out.println("person " +  person.getId());
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static void merge() {
        // pas besoin de persist pour un merge car nous modifions un objet qui n'est pas dans le contexte de persistence, il n'est pas géré par l'entity manager
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select p from Person p where p.id = 1");

        Person person = (Person) query.getSingleResult();

        if(person == null){
            System.out.println("Person not found");
        } else {
            System.out.println("Person : " + person.getPrenom());
            Person person1 = new Person();
            person1.setId(person.getId());
            person1.setPrenom(person.getPrenom());
            person1.setNom("Thirion");

            em.merge(person1);

            person = (Person) query.getSingleResult();

            System.out.println("Person : " + person.getNom());
        }
        em.getTransaction().commit();
        em.close();
        emf.close();

    }

    public static void refresh() {
        // récupère le dernier état de notre table et colonnes, met à jour si a été modifiée par qqn d'autre (dans le cas où la BDD est accessible par plusieurs personnes)

    }

}
