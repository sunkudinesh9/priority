package com.userpriority.priority.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userpriority.priority.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String username);
}
