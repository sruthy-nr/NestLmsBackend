package com.example.NestLmsBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "security")
public class SecurityModel {
    @Id
    @GeneratedValue
    private int id;
    private int seccode;
    private String name;
    private String phone;
    private String email;
    private String password;

    public SecurityModel() {
    }

    public SecurityModel(int id, int seccode, String name, String phone, String email, String password) {
        this.id = id;
        this.seccode = seccode;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeccode() {
        return seccode;
    }

    public void setSeccode(int seccode) {
        this.seccode = seccode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
