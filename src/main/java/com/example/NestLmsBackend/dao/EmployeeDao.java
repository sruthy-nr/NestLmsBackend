package com.example.NestLmsBackend.dao;

import com.example.NestLmsBackend.model.EmployeeModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface EmployeeDao extends CrudRepository<EmployeeModel,Integer> {

    @Query(value = "SELECT `id`, `designation`, `email`, `empcode`, `name`, `password`, `phone`, `salary` FROM `employee` WHERE `empcode`=:empcode",nativeQuery = true)
    public List<EmployeeModel> searchEmployee(@Param("empcode") Integer empcode);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `employee` WHERE `id`= :id",nativeQuery = true)
    public void deleteEmployee(@Param("id") Integer id);

    @Query(value = "SELECT `id`, `designation`, `email`, `empcode`, `name`, `password`, `phone`, `salary` FROM `employee` WHERE `email`=:email AND `password`=:password",nativeQuery = true)
    public List<EmployeeModel> employeeLogin(@Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT `id`, `designation`, `email`, `empcode`, `name`, `password`, `phone`, `salary` FROM `employee` WHERE `id`=:id",nativeQuery = true)
    public List<EmployeeModel> viewProfile(@Param("id") Integer id);

}
