package impl;

import dao.AccountDAO;
import entity.Account;
import entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class AccountDAOImpl implements AccountDAO {
    private EntityManagerFactory entityManagerFactory;

    public AccountDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean addAccount(Account account, long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Customer customer = entityManager.find(Customer.class, customerId);
            account.setCustomer(customer);
            customer.getAccounts().add(account);
            entityManager.persist(account);
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
    public void deleteAccount(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Account account = entityManager.find(Account.class, id);
        if(account != null){
            entityManager.remove(account);
        }
        transaction.commit();
        entityManager.close();

    }

    @Override
    public Account findAccountById(Long accountId) {
        EntityManager localEntityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = localEntityManager.getTransaction();
        transaction.begin();

        Account account = localEntityManager.find(Account.class, accountId);

        transaction.commit();
        localEntityManager.close();

        return account;
    }
}
