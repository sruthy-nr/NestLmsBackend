package com.example.NestLmsBackend.controller;

import com.example.NestLmsBackend.dao.LeaveApplicationDao;
import com.example.NestLmsBackend.dao.LeaveDao;
import com.example.NestLmsBackend.model.EmployeeModel;
import com.example.NestLmsBackend.model.LeaveApplicationModel;
import com.example.NestLmsBackend.model.LeaveModel;
import com.example.NestLmsBackend.model.SecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

@RestController
public class LeaveApplicationController {

    int casual,sick,special;

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
    @PostMapping(path = "viewleave", consumes = "application/json", produces = "application/json")
    public List<LeaveApplicationModel> ViewLeave(@RequestBody LeaveApplicationModel l){
        int sts=0;
        return (List) daol.viewLeave(sts);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/leaveapproved", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> LeaveApproved(@RequestBody LeaveApplicationModel l){


        List<LeaveApplicationModel> result = (List<LeaveApplicationModel>) daol.leaveType(l.getId());
        l.setLeavetype(result.get(0).getLeavetype());


        LocalDate dateBefore = LocalDate.parse(result.get(0).getFromdate());
        LocalDate dateAfter = LocalDate.parse(result.get(0).getTodate());

        // Approach 1
        long daysDiff = ChronoUnit.DAYS.between(dateBefore, dateAfter)+1;
        System.out.println("The number of days between dates: " + daysDiff);


        LeaveModel lm=new LeaveModel();
        List<LeaveModel> result1 = (List<LeaveModel>) dao1.numberOfLeaves(result.get(0).getEmpid());
        casual=result1.get(0).getCasual();
        sick=result1.get(0).getSick();
        special=result1.get(0).getSpecial();
        if (l.getLeavetype().equalsIgnoreCase("casual") && daysDiff<=casual){
            casual= (int) (casual-daysDiff);
            lm.setCasual(casual);
            l.setStatus(1);
            int sts= l.getStatus();
            daol.leaveApproved(l.getId(),sts);
        }
        else if (result.get(0).getLeavetype().equalsIgnoreCase("sick") && daysDiff<=sick) {
            sick= (int) (sick-daysDiff);
            lm.setSick(sick);
            l.setStatus(1);
            int sts= l.getStatus();
            daol.leaveApproved(l.getId(),sts);
        }
        else if (result.get(0).getLeavetype().equalsIgnoreCase("special") && daysDiff<=special){
            special=(int) (special-daysDiff);
            lm.setSpecial(special);
            l.setStatus(1);
            int sts= l.getStatus();
            daol.leaveApproved(l.getId(),sts);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("leavetype", l.getLeavetype());
            String id=String.valueOf(result.get(0).getEmpid());
            map.put("empid", id);
            map.put("message", "no leaves");
            return map;
        }
        dao1.updateLeave(result.get(0).getEmpid(),lm.getCasual(),lm.getSick(),lm.getSpecial());
        HashMap<String,String> map1=new HashMap<>();
        map1.put("status","success");
        return map1;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/leaverejected", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> LeaveRejected(@RequestBody LeaveApplicationModel l){
        l.setStatus(-1);
        int sts= l.getStatus();
        daol.leaveRejected(l.getId(),sts);
        HashMap<String,String> map1=new HashMap<>();
        map1.put("status","success");
        return map1;
    }



}
