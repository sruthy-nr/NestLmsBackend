package com.example.NestLmsBackend.dao;

import com.example.NestLmsBackend.model.LeaveModel;
import org.springframework.data.repository.CrudRepository;

public interface LeaveDao extends CrudRepository<LeaveModel,Integer> {
}
