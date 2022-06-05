package com.codilime.customerservice.service;

import com.codilime.customerservice.dto.CustomerDTO;
import com.codilime.customerservice.model.Customer;
import com.codilime.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {
    private CustomerRepository customerRepository;
    private CustomerService customerService;
    private Customer firstCustomer;
    private Customer secondCustomer;
    private Customer thirdCustomer;
    private Customer fourthCustomer;

    @BeforeEach
    public void init() {
        firstCustomer = generateCustomer("Mia", 15, "Neapol");
        secondCustomer = generateCustomer("Bogdan", 44, "Neapol");
        thirdCustomer = generateCustomer("Steven", 35, "Londyn");
        fourthCustomer = generateCustomer("Mieczyslaw", 66, "Nairobi");

        Set<Customer> customers = Stream.of(firstCustomer, secondCustomer, thirdCustomer, fourthCustomer)
                .collect(Collectors.toCollection(HashSet::new));

        customerRepository = new CustomerRepository(customers);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void shouldFindUniqueCitiesSuccessfully() {
        List<String> uniqueCities = customerService.findUniqueCities();
        assertThat(uniqueCities, hasSize(3));
        assertThat(uniqueCities, hasItems("Neapol", "Londyn", "Nairobi"));
    }

    @Test
    public void shouldFindCustomersByCityAndAgeOverSuccessfully() {
        String city = "Neapol";
        int age = 14;
        List<CustomerDTO> customers = customerService.getCustomersFromCityWithAgeOver(city, age);
        assertThat(customers, hasSize(2));
        customers.forEach(customer -> assertTrue(
                customer.getCity().equals(city) && customer.getAge() > age));
    }

    @Test
    public void shouldFindCustomersByCityAndFilterByAgeSuccessfully() {
        String city = "Neapol";
        int age = 15;
        List<CustomerDTO> customers = customerService.getCustomersFromCityWithAgeOver(city, age);
        assertThat(customers, hasSize(1));
        customers.forEach(customer -> assertTrue(
                customer.getCity().equals(city) && customer.getAge() > age));
    }


    @Test
    public void shouldThrowIllegalArgumentExceptionIfAgeLessThan0() {
        String city = "Neapol";
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                customerService.getCustomersFromCityWithAgeOver(city, -1));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfAgeIsOver131() {
        String city = "Neapol";
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                customerService.getCustomersFromCityWithAgeOver(city, 131));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfCityIsEmpty() {
        String city = "";
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                customerService.getCustomersFromCityWithAgeOver(city, 5));
    }

    @Test
    public void shouldRemoveCustomerByIdSuccessfully() {
        customerService.removeCustomerById(firstCustomer.getId());
        customerService.removeCustomerById(secondCustomer.getId());
        Set<Customer> customers = customerRepository.findAll();
        assertThat(customers, hasSize(2));
        customers.forEach(customer -> assertNotEquals("Neapol", customer.getCity()));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfUserWithIdDoesNotExist() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                customerService.removeCustomerById("some-really-random-uuid"));
    }

    private Customer generateCustomer(String name, int age, String city) {
        return new Customer(name, age, city);
    }
}
