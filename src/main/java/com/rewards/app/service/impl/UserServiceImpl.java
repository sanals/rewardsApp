package com.rewards.app.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rewards.app.form.UsersForm;
import com.rewards.app.form.UsersFormBase;
import com.rewards.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public boolean createUser(UsersForm usersForm, String ... ids ) {
		System.out.println(usersForm.toString());
		System.out.println(usersForm.getUserName() + " : " + usersForm.getPassword());
		Collectors.toList();
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		return true;
	}

	@Override
	public boolean createUser(UsersFormBase usersFormBase, String ... ids ) {
		System.out.println(usersFormBase.toString());
		System.out.println(usersFormBase.getUserName() + " : " + usersFormBase.getPassword());
		Collectors.toList();
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		return true;
	}

}
