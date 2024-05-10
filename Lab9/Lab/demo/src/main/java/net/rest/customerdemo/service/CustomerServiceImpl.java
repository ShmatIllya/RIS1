package net.rest.customerdemo.service;

import lombok.extern.slf4j.Slf4j;
import net.rest.customerdemo.model.Customer;
import net.rest.customerdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;



@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getById(Long id) {
        //    log.info("IN CustomerServiceImpl getById {}");
        return customerRepository.findById(id).get();
    }

    @Override
    public void save(Customer customer) {
        //     log.info("IN CustomerServiceImpl save {}");
        customerRepository.save(customer);
    }

    @Override
    public void saveAll(List<Customer> customers) {
        //     log.info("IN CustomerServiceImpl save {}");
        customerRepository.saveAll(customers);
    }

    @Override
    public void delete(Long id) {
        //  log.info("IN CustomerServiceImpl delete {}");
        customerRepository.deleteById(id);
    }

    @Override
    public void deleteAll(Long[] id) {
        //  log.info("IN CustomerServiceImpl delete {}");
        customerRepository.deleteAllById(Arrays.asList(id));
    }

    @Override
    public List<Customer> getAll() {
        //   log.info("IN CustomerServiceImpl getAll");
        return customerRepository.findAll();
    }
}

