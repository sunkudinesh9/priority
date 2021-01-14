package com.userpriority.priority.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userpriority.priority.model.Priority;
import com.userpriority.priority.model.User;
import com.userpriority.priority.model.UserRequest;
import com.userpriority.priority.model.UserSatisfactionRate;
import com.userpriority.priority.security.JwtUtils;
import com.userpriority.priority.service.PriorityService;
import com.userpriority.priority.service.UserService;

@RestController
@RequestMapping("/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PriorityService priorityService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/user/save")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	// 2. validate user and generate token
	@PostMapping(value = "/login")
	public String login(@RequestBody UserRequest userRequest) {
		// Validate userName/password
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userRequest.getUserName(), userRequest.getPassword()));
		// generate token
		String token = jwtUtils.generateToken(userRequest.getUserName());
		return token;
	}

	@PostMapping("/admin/priority")
	public Priority createPriority(@RequestBody Priority priority) {
		return priorityService.createPriority(priority);
	}

	@GetMapping("/user/getpriority")
	public List<Priority> getAllPriority() {
		return priorityService.getAllPriority();
	}

	@PostMapping("/user/addsatisfactionrate/{userid}")
	public User addPrioritySatisfaction(@RequestBody List<UserSatisfactionRate> ListOfUserSatisfactionRate,
			@PathVariable Integer userid) {
		return userService.addPrioritySatisfaction(userid, ListOfUserSatisfactionRate);
	}

}