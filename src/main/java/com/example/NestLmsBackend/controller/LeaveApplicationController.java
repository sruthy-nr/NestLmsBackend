package com.example.NestLmsBackend.controller;

import com.example.NestLmsBackend.dao.LeaveApplicationDao;
import com.example.NestLmsBackend.dao.LeaveDao;
import com.example.NestLmsBackend.model.EmployeeModel;
import com.example.NestLmsBackend.model.LeaveApplicationModel;
import com.example.NestLmsBackend.model.LeaveModel;
import com.example.NestLmsBackend.model.SecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class LeaveApplicationController {

    public static int casual=20;
    public static int sick=7;
    public static int special=3;

    @Autowired
    LeaveApplicationDao daol;

    @Autowired
    LeaveDao dao1;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/applyleave", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> ApplyLeave(@RequestBody LeaveApplicationModel l){
        HashMap<String,String> map=new HashMap<>();
        daol.save(l);
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/leaveapproved", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> LeaveApproved(@RequestBody LeaveApplicationModel l){

        l.setStatus(1);
        int sts= l.getStatus();
        daol.leaveApproved(l.getId(),sts);
        List<LeaveApplicationModel> result = (List<LeaveApplicationModel>) daol.leaveType(l.getId());
        l.setLeavetype(result.get(0).getLeavetype());
        LeaveModel lm=new LeaveModel();
        if (l.getLeavetype()=="casual"){
            casual--;
            lm.setCasual(casual);
        }
        else if (l.getLeavetype()=="sick") {
            sick--;
            lm.setSick(sick);
        }
        else if (l.getLeavetype()=="special"){
            special--;
            lm.setSpecial(special);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("leavetype", l.getLeavetype());
            String id=String.valueOf(result.get(0).getEmpid());
            map.put("empid", id);
            return map;
        }
        dao1.updateLeave(result.get(0).getEmpid(),lm.getCasual(),lm.getSick(),lm.getSpecial());
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

}
