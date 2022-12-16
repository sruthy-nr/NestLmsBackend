package com.example.NestLmsBackend.dao;

import com.example.NestLmsBackend.model.LogsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LogsDao extends CrudRepository<LogsModel,Integer> {

@Modifying
    @Transactional
@Query(value = "UPDATE `logs` SET `exitdatetime`=:exittime WHERE `id`=:id",nativeQuery = true)
    public void updateExit(@Param("id") Integer id,@Param("exittime") String exittime);

@Query(value = "SELECT `id`, `empcode`, `entrydatetime`, `exitdatetime` FROM `logs` WHERE `entrydatetime` LIKE %:dt% AND`exitdatetime` LIKE %:dt%",nativeQuery = true)
    public List<LogsModel> dailyLogs(@Param("dt") String dt);

    @Query(value = "SELECT `id`, `empcode`, `entrydatetime`, `exitdatetime` FROM `logs` WHERE `entrydatetime` BETWEEN :from AND :to OR `exitdatetime` BETWEEN :from AND :to",nativeQuery = true)
    public List<LogsModel> datewiseLogs(@Param("from") String from,@Param("to") String to);
}
