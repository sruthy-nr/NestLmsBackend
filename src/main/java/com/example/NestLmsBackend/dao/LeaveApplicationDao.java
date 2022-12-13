package com.example.NestLmsBackend.dao;

import com.example.NestLmsBackend.model.EmployeeModel;
import com.example.NestLmsBackend.model.LeaveApplicationModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveApplicationDao extends CrudRepository<LeaveApplicationModel,Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leaveapplication` SET `status`= :sts WHERE `id`=:id",nativeQuery = true)
    public void leaveApproved(@Param("id") Integer id, @Param("sts") Integer sts);

    @Query(value = "SELECT `id`, `applydate`, `empid`, `fromdate`, `leavetype`, `remarks`, `status`, `todate` FROM `leaveapplication` WHERE `id`=:id",nativeQuery = true)
    public List<LeaveApplicationModel> leaveType(@Param("id") Integer id);
}
