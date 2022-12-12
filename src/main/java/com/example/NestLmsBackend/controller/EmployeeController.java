package com.example.NestLmsBackend.controller;

import com.example.NestLmsBackend.dao.EmployeeDao;
import com.example.NestLmsBackend.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeDao dao;
    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public String Homepage(){
        return "Welcome to my website";
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addemployee", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> AddEmployee(@RequestBody EmployeeModel e){
        HashMap<String,String> map=new HashMap<>();
        dao.save(e);
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/viewemployee")
    public List<EmployeeModel> ViewEmployee(){
        return (List<EmployeeModel>) dao.findAll();
    }


}
