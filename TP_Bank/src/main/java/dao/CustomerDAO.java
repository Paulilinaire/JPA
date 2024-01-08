package dao;

import entity.Customer;

public interface CustomerDAO {
    public boolean addCustomer(Customer customer, long agencyId);

    public void deleteCustomer(Long id);

    public Customer findCustomerById(Long customerId);
}
