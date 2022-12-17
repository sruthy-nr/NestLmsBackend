package com.example.NestLmsBackend.controller;

import com.example.NestLmsBackend.dao.VisitorDao;
import com.example.NestLmsBackend.model.LogsModel;
import com.example.NestLmsBackend.model.SecurityModel;
import com.example.NestLmsBackend.model.VisitorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class VisitorController {

    @Autowired
    VisitorDao daov;
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/visitorentry", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> VisitorEntry(@RequestBody VisitorModel v){
        HashMap<String,String> map=new HashMap<>();
        daov.save(v);
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/viewvisitor")
    public List<VisitorModel> ViewVisitor(){
        return (List<VisitorModel>) daov.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/visitorexit", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> VisitorExit(@RequestBody VisitorModel v) {

        daov.visitorExit(v.getId(), v.getExitdatetime());
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "visitordailylogs", consumes = "application/json", produces = "application/json")
    public List<VisitorModel> VisitorDailyLogs(@RequestBody VisitorModel v){
        String from= v.getEntrydatetime();
        return (List<VisitorModel>) daov.visitorDailyLogs(from);
    }
}
