package dao;

import entity.Account;

public interface AccountDAO {

    public boolean addAccount(Account account, long id);

    public void deleteAccount(Long id);

    public Account findAccountById(Long customerId);

}
