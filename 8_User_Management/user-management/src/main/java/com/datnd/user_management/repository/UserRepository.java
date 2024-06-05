package com.datnd.user_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datnd.user_management.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    
}
