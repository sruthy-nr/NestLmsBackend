package com.example.NestLmsBackend.dao;

import com.example.NestLmsBackend.model.EmployeeModel;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<EmployeeModel,Integer> {
}
