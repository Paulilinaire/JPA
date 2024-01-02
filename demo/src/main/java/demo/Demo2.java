package demo;

import entity.Person;

import javax.persistence.*;
import java.util.List;

public class Demo2 {
    public static void main() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transac = em.getTransaction();

        transac.begin();
        try{
            Person person = em.getReference(Person.class, 1L); // .getReference() = .find()
            System.out.println("person n°1 :" + person);
        }catch (EntityNotFoundException e){
            System.out.println("Entity non trouvée : " + e.getMessage());
        }

        // Récup par Query (ici recup par nom)
        // singleResult:
//        Query query = em.createQuery("select p from Person p where p.nom = 'Alouette'", Person.class);
//        Person person = (Person) query.getSingleResult(); // (Person) est un cast = on lui dit de récupérer un type person
//        System.out.println(person);

//        em.remove(person);
//        transac.commit();

        // resultList
        Query query1 = em.createQuery("select p from Person p where p.nom = 'Amoitié'", Person.class);
        List<Person> personList = query1.getResultList();

        for (Person p: personList){
            System.out.println("person s'appelant Amoitié: " + p);
        }

        // Paramètres nommés
        Query query2 = em.createQuery("select p from Person p where p.id > : id");
        query2.setParameter("id", 4L );
        List<Person> personList2 = query2.getResultList();

        for (Person p: personList2){
            System.out.println("person trouvée avec paramètre nommé : " + p.getNom());
        }

        System.out.println(" ------------ Modifier une occurence ------------");

        Person person4 = em.find(Person.class, 4L);
        person4.setNom("Pesquet");
        person4.setPrenom("Thomas");

        transac.commit();
        person4 = em.find(Person.class, 4L);
        System.out.println("Person n°4 : " + person4);


        System.out.println("------------ Rollback ------------");

        Person person5 = new Person("Jim", "Paul");
        em.persist(person5);

        if(person5.getId()==11L){
            System.out.println("person rollback " + person5.getId()); // person n°11 n'est pas enregistré en BDD car est revenu en arrière (rollback)
            transac.rollback();
        } else {
            transac.commit();
            System.out.println("person " + person5.getId());
        }

        System.out.println("------------ Native Query ------------ ");
        List<Person> personList5 = em.createNativeQuery("SELECT * FROM person", Person.class).getResultList();
        for (Person p: personList5) {
            System.out.println("p : ");
        }

        em.close();
        emf.close();

    }

}
