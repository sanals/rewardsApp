package com.rewards.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.app.form.UsersForm;
import com.rewards.app.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	UserService userService;

	@GetMapping("/ok")
	public ResponseEntity<Void> okBuild() {
		return ResponseEntity.ok().build();// response 200
	}

	@GetMapping("/tests")
	@Operation(description = "tests get request", summary = "tests summary")
	public ResponseEntity<String> tests() {
		return ResponseEntity.ok("tests");
	}

	@GetMapping("/tests2")
	@Operation(description = "tests get request", summary = "tests summary")
	@ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK, reason = "ResponseStatus reason")
	public ResponseEntity<String> tests2() {
		return ResponseEntity.ok("tests2");
	}

}
