package com.codilime.customerservice.service;

import com.codilime.customerservice.dto.CustomerDTO;
import com.codilime.customerservice.model.Customer;
import com.codilime.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void removeCustomerById(String customerId) {
        //TODO As I User I want to remove customers by id.
        boolean check = false;
        this.customerRepository.findAll().stream().forEach((c) ->
                {
                    if (c.getId().compareToIgnoreCase(customerId) == 0) {
                        this.customerRepository.findAll().remove(c);
                        return;
                    }

                }

        );
        // If customer with given id does not exist IllegalArgumentException should be thrown
        throw new UnsupportedOperationException();
    }

    public List<String> findUniqueCities() {
        //TODO As I User I want to get all unique cities in which live our customers to feed filter on frontend side
        // e.g. We have 5 customers in our repository. 2 from Belgrad, 2 from Nairobi, 1 from Sosnowiec. We should get
        // list with three values - Belgrad, Nairobi, Sosnowiec.
        ArrayList<String> cities = new ArrayList<>();
        ArrayList<String> emp = new ArrayList<>();
        this.customerRepository.findAll().stream().forEach((c) ->
                {
                    emp.add(c.getCity());

                }
        );
        for (int i = 0; i < emp.size(); i++) {
            if (!(cities.indexOf(emp.get(i)) > 0)) {
                cities.add(emp.get(i));
            }

        }
        return cities;
    }

    public List<CustomerDTO> getCustomersFromCityWithAgeOver(String city, int age) {
        //TODO As I User I want to get customers from applied city and with age over passed parameter.
        // e.g. We have three customers in our repository.
        // Michal - from Sosnowiec, 18 years old
        // Bogdan - from Neapol, 45 yers old
        // Mia - from Neapol, 33 yers old
        // If we call method with parameters Neapol and 31 we should het Mia and Bogdan.
        // If we call method with parameters Neapol and 40 we should get only Bogdan.
        // If city parameter is empty or/and age is over 130 or less than 1 throw IllegalArgumentException with message.
        if (city.isEmpty() && age > 130 || age < 1)
            throw new UnsupportedOperationException();

        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        this.customerRepository.findAll().stream().forEach((c) ->
                {
                    if (city.compareToIgnoreCase(c.getCity()) == 0 &&
                            age == c.getAge()
                    ) {
                        CustomerDTO customerDTO = new CustomerDTO();
                        customerDTO.setAge(c.getAge());
                        customerDTO.setCity(c.getCity());
                        customerDTOS.add(customerDTO);
                    }

                }
        );
        return customerDTOS;

    }


}
