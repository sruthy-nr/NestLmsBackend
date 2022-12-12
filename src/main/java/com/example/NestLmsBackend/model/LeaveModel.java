package com.example.NestLmsBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "leaves")
public class LeaveModel {

    @Id
    @GeneratedValue
    private int id;
    private int empid;
    private int casual=20;
    private int sick=7;
    private int special=3;
    private String year= new SimpleDateFormat("yyyy").format(new Date());


    public LeaveModel() {
    }

    public LeaveModel(int id, int empid, int casual, int sick, int special, String year) {
        this.id = id;
        this.empid = empid;
        this.casual = casual;
        this.sick = sick;
        this.special = special;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public int getCasual() {
        return casual;
    }

    public void setCasual(int casual) {
        this.casual = casual;
    }

    public int getSick() {
        return sick;
    }

    public void setSick(int sick) {
        this.sick = sick;
    }

    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year=year;
    }
}
