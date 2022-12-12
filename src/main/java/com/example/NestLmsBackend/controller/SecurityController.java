package com.example.NestLmsBackend.controller;

import com.example.NestLmsBackend.dao.SecurityDao;
import com.example.NestLmsBackend.model.EmployeeModel;
import com.example.NestLmsBackend.model.LeaveModel;
import com.example.NestLmsBackend.model.SecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class SecurityController {

    @Autowired
    SecurityDao daos;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addsecurity", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> AddSecurity(@RequestBody SecurityModel s){
        HashMap<String,String> map=new HashMap<>();
        daos.save(s);
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/viewsecurity")
    public List<SecurityModel> ViewSecurity(){
        return (List<SecurityModel>) daos.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "searchSecurity", consumes = "application/json", produces = "application/json")
    public List<SecurityModel> SearchSecurity(@RequestBody SecurityModel s){
        return (List<SecurityModel>) daos.searchSecurity(s.getSeccode());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/deletesecurity", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> DeleteSecurity(@RequestBody SecurityModel s){

        daos.deleteSecurity(s.getId());
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/securitylogin", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> SecurityLogin(@RequestBody SecurityModel s) {

        String email = String.valueOf(s.getEmail());
        String password = String.valueOf(s.getPassword());
        List<SecurityModel> result = (List<SecurityModel>) daos.securityLogin(email, password);
        HashMap<String, String> map = new HashMap<>();
        if(result.size()==0){
            map.put("status","failed");
        }
        else {
            int id=result.get(0).getId();
            map.put("secid",String.valueOf(id));
            map.put("status","success");
        }
        return  map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewsecurityprofile", consumes = "application/json", produces = "application/json")
    public List<SecurityModel> ViewSecurityProfile(@RequestBody SecurityModel s){
        return (List<SecurityModel>) daos.viewSecurityProfile(s.getId());
    }

}
