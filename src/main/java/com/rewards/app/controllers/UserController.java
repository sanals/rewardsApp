package com.rewards.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.app.exception.BadRequestException;
import com.rewards.app.form.UsersForm;
import com.rewards.app.form.UsersFormBase;
import com.rewards.app.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/getAny")
	public ResponseEntity<Boolean> getAny() {
		return ResponseEntity.ok(true);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/getRoleUser")
	public ResponseEntity<Boolean> getRoleUser() {
		return ResponseEntity.ok(true);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getUser")
	public ResponseEntity<Boolean> getUser() {
		return ResponseEntity.ok(true);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/getRoleAdmin")
	public ResponseEntity<Boolean> getRoleAdmin() {
		return ResponseEntity.ok(true);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAdmin")
	public ResponseEntity<Boolean> getAdmin() {
		return ResponseEntity.ok(true);
	}

	/**
	 * A method named createUser
	 * 
	 * @param usersForm An object of UsersForm
	 * @param pathId    A String object for pathId
	 * @param paramId   A String object for paramId
	 * @return ResponseEntity<Boolean> This returns a ResponseEntity with Boolean
	 *         parameter in it
	 */
	@PostMapping("/createUser/{pathId}")
	@Operation(summary = "Operation summary createUser", description = "Operation description createUser")
	public ResponseEntity<Boolean> createUser(@Valid @RequestBody UsersForm usersForm,
			@PathVariable(value = "pathId", required = false) String pathId, // will always show as 'required=true' in
																				// swagger
			@RequestParam(value = "paramId", required = false) String paramId) {
		return ResponseEntity.ok(userService.createUser(usersForm, pathId, paramId));
	}

	/**
	 * A method named createUser
	 * 
	 * @param usersForm An object of UsersForm
	 * @param pathId1   A String object for pathId1
	 * @param pathId2   A String object for pathId2
	 * @param paramId1  A String object for paramId1
	 * @param paramId2  A String object for paramId2
	 * @return ResponseEntity<Boolean> This returns a ResponseEntity with Boolean
	 *         parameter in it
	 */
	@PostMapping("/createUser2/{pathId1}/{pathId2}")
	@Operation(summary = "Operation summary createUser2", description = "Operation description createUser2")
	public ResponseEntity<Boolean> createUser2(@Valid @RequestBody UsersForm usersForm,
			@PathVariable(value = "pathId1", required = false) String pathId1, // @PathVariable will always show as
																				// 'required=true' in swagger
			@PathVariable(value = "pathId2", required = false) String pathId2, // @PathVariable will always show as
																				// 'required=true' in swagger
			@RequestParam(value = "paramId1", required = false) String paramId1,
			@RequestParam(value = "paramId2", required = false) String paramId2) {
		return ResponseEntity.ok(userService.createUser(usersForm, pathId1, pathId2, paramId1, paramId2));
	}

	@PostMapping("/createUser3")
	public ResponseEntity<Boolean> createUser3(@Valid @RequestBody UsersForm usersForm) {
		return ResponseEntity.ok(userService.createUser(usersForm));
	}

	@PostMapping("/createUser4")
	public ResponseEntity<Boolean> createUser4(@RequestBody UsersForm usersForm) {
		return ResponseEntity.ok(userService.createUser(usersForm));
	}
	
	@PostMapping("/createUser5")
	public ResponseEntity<Boolean> createUser5(@Valid @RequestBody UsersFormBase usersFormBase, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return ResponseEntity.ok(userService.createUser(usersFormBase));

    }
	
	@PostMapping("/createUser6")
	public ResponseEntity<Boolean> createUser6(@Valid @RequestBody UsersForm usersForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return ResponseEntity.ok(userService.createUser(usersForm));

    }

}
