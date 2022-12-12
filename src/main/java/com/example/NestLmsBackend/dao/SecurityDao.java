package com.example.NestLmsBackend.dao;

import com.example.NestLmsBackend.model.SecurityModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecurityDao extends CrudRepository<SecurityModel,Integer> {

    @Query(value = "SELECT `id`, `email`, `name`, `password`, `phone`, `seccode` FROM `security` WHERE `seccode`=:seccode",nativeQuery = true)
    public List<SecurityModel> searchSecurity(@Param("seccode") Integer seccode);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `security` WHERE `id`=:id",nativeQuery = true)
    public void deleteSecurity(@Param("id") Integer id);

    @Query(value = "SELECT `id`, `email`, `name`, `password`, `phone`, `seccode` FROM `security` WHERE `email`=:email AND `password`=:password",nativeQuery = true)
    public List<SecurityModel> securityLogin(@Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT `id`, `email`, `name`, `password`, `phone`, `seccode` FROM `security` WHERE `id`=:id",nativeQuery = true)
    public List<SecurityModel> viewSecurityProfile(@Param("id") Integer id);
}
