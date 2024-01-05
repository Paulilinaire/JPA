package dao;

import entity.Account;

public interface AccountDAO {

    public void addAccount(Account account);

    public void deleteAccount(Long id);

    public Account findAccountById(Long customerId);

}
