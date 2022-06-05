package com.codilime.customerservice.dto;

import com.codilime.customerservice.model.Customer;

public class CustomerDTO {
    private String name;
    private int age;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static CustomerDTO from(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCity(customer.getCity());
        customerDTO.setAge(customer.getAge());
        customerDTO.setName(customer.getName());
        return customerDTO;
    }
}
