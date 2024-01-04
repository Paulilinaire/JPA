package demo;

import entity.oneToOne.Adress;
import entity.oneToOne.House;
import entity.oneToOne.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Demo4 {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");

    public static void main() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Adress adress = new Adress();

        adress.setNumero(46);
        adress.setCodePostal("59170");
        adress.setNomRue("rue des fleurs");
        adress.setVille("Londres");

        House house = new House();
        house.setTaille(500);
        house.setType(Type.OLD);
        house.setAdress(adress);

        em.persist(house);
        em.persist(adress);
        em.getTransaction().commit();

        House house1 = em.find(House.class, house.getId());
        System.out.println("house : " + house1);

        em.close();
        emf.close();


    }







}
