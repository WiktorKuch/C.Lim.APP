package com.codilime.customerservice.repository;

import com.codilime.customerservice.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.function.Predicate;

@Repository
public class CustomerRepository {
    private Set<Customer> customers;

    public CustomerRepository(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Customer> findAll() {
        return customers;
    }

    public void removeIf(Predicate<Customer> removeCondition) {
        this.customers.removeIf(removeCondition);
    }
}
