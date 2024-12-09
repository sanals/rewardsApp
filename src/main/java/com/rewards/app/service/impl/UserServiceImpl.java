package com.rewards.app.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

import com.rewards.app.entities.Users;
import com.rewards.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rewards.app.form.UsersForm;
import com.rewards.app.form.UsersFormBase;
import com.rewards.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@Autowired
	UserRepo userRepo;

	@Override
	public boolean createUser(UsersForm usersForm, String ... ids ) {
		System.out.println(usersForm.toString());
		System.out.println(usersForm.getUserName() + " : " + usersForm.getPassword());
		Collectors.toList();
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		Users users = new Users();
		users.setUserId(UUID.randomUUID().toString());
		users.setPassword(encoder.encode(usersForm.getPassword()));
		users.setUserName(usersForm.getUserName());
		LocalDateTime now = LocalDateTime.now();
		users.setCreatedTime(now);
		users.setUpdatedTime(now);

		userRepo.save(users);

		return true;
	}

	@Override
	public boolean updateUser(UsersForm usersForm, String userId ) {
		System.out.println(usersForm.toString());
		System.out.println(usersForm.getUserName() + " : " + usersForm.getPassword());
		Users users = userRepo.findById(userId).get();
		users.setUserId(userId);
		users.setPassword(encoder.encode(usersForm.getPassword()));
		users.setUserName(usersForm.getUserName());
		users.setUpdatedTime(LocalDateTime.now());
		userRepo.save(users);
		return true;
	}

}
