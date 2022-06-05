package com.codilime.customerservice.model;

import java.util.UUID;

public class Customer {
    private String id;
    private String name;
    private int age;
    private String city;

    public Customer(String name, int age, String city) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
