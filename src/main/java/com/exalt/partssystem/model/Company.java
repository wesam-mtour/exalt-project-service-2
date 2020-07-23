package com.exalt.partssystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;

@Document(collection = "company")

public class Company {
    @Id
    private String id;
    private String name;
    private Address address;

    public Company() {
    }

    public Company(String name, Address address) {
        this.name = name;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
