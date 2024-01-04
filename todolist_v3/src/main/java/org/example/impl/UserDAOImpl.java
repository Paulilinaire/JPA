package org.example.impl;

import org.example.dao.TaskDAO;
import org.example.dao.UserDAO;
import org.example.model.Task;
import org.example.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private EntityManagerFactory entityManagerFactory;

    private static TaskDAOImpl taskDAO;

    public UserDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public boolean addUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Task> getUsersTasks(long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            // Recherche de l'utilisateur dans la base de données
            User user = entityManager.find(User.class, userId);

            if (user != null) {
                // Récupération de la liste des tâches associées à l'utilisateur
                List<Task> tasks = new ArrayList<>(user.getTaskList());

                transaction.commit();

                return tasks;
            } else {
                System.out.println("Aucun utilisateur trouvé");
                return null;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }


    @Override
    public boolean deleteUser(long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user= entityManager.find(User.class, userId);
            if (user != null) {
                entityManager.remove(user);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }



}

