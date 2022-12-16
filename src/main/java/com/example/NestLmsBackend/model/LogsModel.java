package com.example.NestLmsBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "logs")
public class LogsModel {
    @Id
    @GeneratedValue
    private int id;
    private int empcode;
    private String entrydatetime;
    private String exitdatetime;

    public LogsModel() {
    }

    public LogsModel(int id, int empcode, String entrydatetime, String exitdatetime) {
        this.id = id;
        this.empcode = empcode;
        this.entrydatetime = entrydatetime;
        this.exitdatetime = exitdatetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpcode() {
        return empcode;
    }

    public void setEmpcode(int empcode) {
        this.empcode = empcode;
    }

    public String getEntrydatetime() {
        return entrydatetime;
    }

    public void setEntrydatetime(String entrydatetime) {
        this.entrydatetime = entrydatetime;
    }

    public String getExitdatetime() {
        return exitdatetime;
    }

    public void setExitdatetime(String exitdatetime) {
        this.exitdatetime = exitdatetime;
    }
}
