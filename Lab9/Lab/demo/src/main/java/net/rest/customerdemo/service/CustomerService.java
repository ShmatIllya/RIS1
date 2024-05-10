package net.rest.customerdemo.service;


import net.rest.customerdemo.model.Customer;

import java.util.List;


public interface CustomerService {

    Customer getById(Long id);

    void save(Customer customer);

    void saveAll(List<Customer> customers);

    void delete(Long id);

    void deleteAll(Long[] id);

    List<Customer> getAll();
}

