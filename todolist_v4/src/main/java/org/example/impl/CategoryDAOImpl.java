package org.example.impl;

import org.example.dao.CategoryDAO;
import org.example.model.Category;
import org.example.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private EntityManagerFactory entityManagerFactory;

    public CategoryDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void addCategory(Category category) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(category);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public void deleteCategory(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Category category = entityManager.find(Category.class, id);
        if(category != null){
            entityManager.remove(category);
        }
        transaction.commit();
        entityManager.close();

    }

    @Override
    public Category findCategoryById(Long categoryId) {
        EntityManager localEntityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = localEntityManager.getTransaction();
        transaction.begin();

        Category category = localEntityManager.find(Category.class, categoryId);

        transaction.commit();
        localEntityManager.close();

        return category;
    }

}
