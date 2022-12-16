package com.example.NestLmsBackend.controller;

import com.example.NestLmsBackend.dao.EmployeeDao;
import com.example.NestLmsBackend.dao.LogsDao;
import com.example.NestLmsBackend.model.EmployeeModel;
import com.example.NestLmsBackend.model.LeaveApplicationModel;
import com.example.NestLmsBackend.model.LogsModel;
import com.example.NestLmsBackend.model.SecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class LogsController {
    @Autowired
    LogsDao daol;
    @Autowired
    EmployeeDao daoe;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/entry", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> Entry(@RequestBody LogsModel l){
        HashMap<String,String> map=new HashMap<>();
        daol.save(l);
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/viewexitlist")
    public List<LogsModel> ViewExitList(){
        return (List<LogsModel>) daol.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/exit", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> Exit(@RequestBody LogsModel l){
        daol.updateExit(l.getId(), l.getExitdatetime());
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "dailylogs", consumes = "application/json", produces = "application/json")
    public List<LogsModel> DailyLogs(@RequestBody LogsModel l){
        String dt= l.getEntrydatetime();
        return (List<LogsModel>) daol.dailyLogs(dt);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "datewiselogs", consumes = "application/json", produces = "application/json")
    public List<LogsModel> DatewiseLogs(@RequestBody LogsModel l){
        String from= l.getEntrydatetime();
        String to=l.getExitdatetime();
        return (List<LogsModel>) daol.datewiseLogs(from,to);
    }
}
