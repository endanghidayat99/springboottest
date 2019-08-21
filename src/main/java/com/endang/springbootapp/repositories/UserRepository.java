package com.endang.springbootapp.repositories;

import com.endang.springbootapp.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

}
