package com.example.springmysqlhello.repository;

import com.example.springmysqlhello.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Integer> {

}
