package dao;

import entity.Customer;

public interface CustomerDAO {
    public void addCustomer(Customer customer);

    public void deleteCustomer(Long id);

    public Customer findCustomerById(Long customerId);
}
