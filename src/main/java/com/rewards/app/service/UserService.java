package com.rewards.app.service;

import com.rewards.app.form.UsersForm;
import com.rewards.app.form.UsersFormBase;

public interface UserService {
	public boolean createUser(UsersForm usersForm, String ... ids);
	public boolean updateUser(UsersForm usersForm, String userId);
}
