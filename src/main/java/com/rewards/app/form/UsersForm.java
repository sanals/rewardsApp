package com.rewards.app.form;

import com.rewards.app.form.customvalidator.Password;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Schema for UsersForm")
public class UsersForm extends UsersFormBase {
	@NotBlank // auto adds required indicator(*) in swagger
	@Size(min = 2, max = 16)
	@Schema(description = "description for UsersForm User Name", example = "Sanal S")
	private String userName;
//	@Password
	@NotBlank
	@Schema(description = "description for UserForm password", example = "password...")
	private String password;

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UsersForm [userName=" + userName + ", password=" + password + "]";
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

}
