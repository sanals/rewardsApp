package com.rewards.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rewards.app.entities.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, String>{

	Users findByUserName(String userName);
}
