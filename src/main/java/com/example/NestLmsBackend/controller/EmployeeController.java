package com.example.NestLmsBackend.controller;

import com.example.NestLmsBackend.dao.EmployeeDao;
import com.example.NestLmsBackend.dao.LeaveDao;
import com.example.NestLmsBackend.model.EmployeeModel;
import com.example.NestLmsBackend.model.LeaveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeDao dao;

    @Autowired
    LeaveDao daol;


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
        map.put("id",String.valueOf(e.getId()));
        LeaveModel l=new LeaveModel();
        l.setEmpid(e.getId());
        daol.save(l);
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/viewemployee")
    public List<EmployeeModel> ViewEmployee(){
        return (List<EmployeeModel>) dao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "searchemployee", consumes = "application/json", produces = "application/json")
    public List<EmployeeModel> SearchEmployee(@RequestBody EmployeeModel e){
        return (List<EmployeeModel>) dao.searchEmployee(e.getEmpcode());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/deleteemployee", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> DeleteEmployee(@RequestBody EmployeeModel e){
        String eid=String.valueOf(e.getId());
        System.out.println(eid);
        dao.deleteEmployee(e.getId());
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/employeelogin", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> EmployeeLogin(@RequestBody EmployeeModel e) {

        String email = String.valueOf(e.getEmail());
        String password = String.valueOf(e.getPassword());
        List<EmployeeModel> result = (List<EmployeeModel>) dao.employeeLogin(email, password);
        HashMap<String, String> map = new HashMap<>();
        if(result.size()==0){
            map.put("status","failed");
        }
        else {
            int id=result.get(0).getId();
            map.put("empid",String.valueOf(id));
            map.put("status","success");
        }
        return  map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewprofile", consumes = "application/json", produces = "application/json")
    public List<EmployeeModel> ViewProfile(@RequestBody EmployeeModel e){
        return (List<EmployeeModel>) dao.viewProfile(e.getId());
    }

}
