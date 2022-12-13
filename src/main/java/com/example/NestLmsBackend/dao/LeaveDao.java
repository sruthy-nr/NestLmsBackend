package com.example.NestLmsBackend.dao;

import com.example.NestLmsBackend.model.LeaveModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LeaveDao extends CrudRepository<LeaveModel,Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leaves` SET `casual`=:casual,`sick`=:sick,`special`=:special WHERE `empid`=:empid", nativeQuery = true)
    public void updateLeave(@Param("empid") Integer empid, @Param("casual") Integer casual, @Param("sick") Integer sick, @Param("special") Integer special);
}
