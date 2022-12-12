package com.example.NestLmsBackend.dao;

import com.example.NestLmsBackend.model.SecurityModel;
import org.springframework.data.repository.CrudRepository;

public interface SecurityDao extends CrudRepository<SecurityModel,Integer> {
}
