package com.userpriority.priority.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userpriority.priority.model.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {

}
