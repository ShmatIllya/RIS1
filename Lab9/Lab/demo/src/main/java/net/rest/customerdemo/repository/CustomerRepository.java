package net.rest.customerdemo.repository;


import net.rest.customerdemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

