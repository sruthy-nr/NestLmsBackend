package com.example.NestLmsBackend.dao;

import com.example.NestLmsBackend.model.LogsModel;
import com.example.NestLmsBackend.model.VisitorModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitorDao extends CrudRepository<VisitorModel,Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE `visitor` SET `exitdatetime`=:exitdatetime WHERE `id`=:id",nativeQuery = true)
    public void visitorExit(@Param("id") Integer id,@Param("exitdatetime") String exitdatetime);

    @Query(value = "SELECT `id`, `name`, `entrydatetime`, `exitdatetime`, `purpose`, `whomtomeet` FROM `visitor` WHERE `entrydatetime` LIKE %:from%",nativeQuery = true)
    public List<VisitorModel> visitorDailyLogs(@Param("from") String from);
}
