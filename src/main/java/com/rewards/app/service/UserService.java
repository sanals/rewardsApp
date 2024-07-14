package com.rewards.app.service;

import com.rewards.app.form.UsersForm;
import com.rewards.app.form.UsersFormBase;

public interface UserService {
	public boolean createUser(UsersForm usersForm, String ... ids);
	public boolean createUser(UsersFormBase usersFormBase, String ... ids);
}
