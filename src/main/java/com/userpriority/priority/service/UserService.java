package com.userpriority.priority.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.userpriority.priority.exceptionhandling.UserNotFounDException;
import com.userpriority.priority.model.User;
import com.userpriority.priority.model.UserSatisfactionRate;
import com.userpriority.priority.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrytPasswordEncode;

	/*
	 * Create a new user
	 */
	public User createUser(User user) {
		user.setPasword(bcrytPasswordEncode.encode(user.getPasword()));
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	/*
	 * Find the user by ID
	 */
	public User findById(Integer userId) {
		return userRepository.findById(userId).get();
	}
	
	public User findByUserName(String userName) {
		Optional<User> optionalUser = userRepository.findByUserName(userName);
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("wrong username" + userName));
		return optionalUser.get();
	}

	/*
	 * Adding the Satisfaction rate to the user
	 */
	public User addPrioritySatisfaction(Integer userId, List<UserSatisfactionRate> listOfUserSatisfactionRate) {
		User user = null;
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
			// Adding the userSatisfactionrate to the user
			for (UserSatisfactionRate serSatisfactionRate : listOfUserSatisfactionRate) {
				user.getPriorityList().add(serSatisfactionRate);
			}
			userRepository.save(user);
		} else {
			throw new UserNotFounDException("Unable to find the user");
		}
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUserName(username);
		return new org.springframework.security.core.userdetails.User(
				username,
				user.getPasword(),
				user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList())
				);
	}

}
