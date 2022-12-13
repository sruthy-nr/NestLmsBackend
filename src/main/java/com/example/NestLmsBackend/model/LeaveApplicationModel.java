package com.example.NestLmsBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leaveapplication")
public class LeaveApplicationModel {

    @Id
    @GeneratedValue
    private int id;
    private int empid;
    private String leavetype;
    private String remarks;
    private String fromdate;
    private String todate;
    private String applydate;
    private int status=0;

    public LeaveApplicationModel() {
    }

    public LeaveApplicationModel(int id, int empid, String leavetype, String remarks, String fromdate, String todate, String applydate, int status) {
        this.id = id;
        this.empid = empid;
        this.leavetype = leavetype;
        this.remarks = remarks;
        this.fromdate = fromdate;
        this.todate = todate;
        this.applydate = applydate;
        this.status = status;
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

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getApplydate() {
        return applydate;
    }

    public void setApplydate(String applydate) {
        this.applydate = applydate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
