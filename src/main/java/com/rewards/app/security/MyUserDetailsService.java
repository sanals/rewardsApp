package com.rewards.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rewards.app.entities.Users;
import com.rewards.app.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findByUserName(username);
		if(user==null) {
			System.out.println("User not found null...");
			throw new UsernameNotFoundException(username+" not found");
		}
		return new UserPrincipal(user);
	}

}
