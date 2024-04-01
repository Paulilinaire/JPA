package impl;

import dao.CustomerDAO;
import entity.Account;
import entity.Agency;
import entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class CustomerDAOImpl implements CustomerDAO {
    private EntityManagerFactory entityManagerFactory;

    public CustomerDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean addCustomer(Customer customer, long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Agency agency = entityManager.find(Agency.class, agencyId);
            customer.setAgency(agency);
            agency.getCustomers().add(customer);
            entityManager.persist(customer);
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
    public void deleteCustomer(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, id);
        if(customer != null){
            entityManager.remove(customer);
        }
        transaction.commit();
        entityManager.close();
    }

    @Override
    public Customer findCustomerById(Long customerId) {
        EntityManager localEntityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = localEntityManager.getTransaction();
        transaction.begin();

        Customer customer = localEntityManager.find(Customer.class, customerId);

        transaction.commit();
        localEntityManager.close();

        return customer;
    }
}
