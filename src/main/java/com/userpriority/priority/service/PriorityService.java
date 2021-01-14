package com.userpriority.priority.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userpriority.priority.model.Priority;
import com.userpriority.priority.repository.PriorityRepository;

@Service
public class PriorityService {
	@Autowired
	private PriorityRepository priorityRepository;

	public Priority createPriority(Priority priority) {
		return priorityRepository.save(priority);
	}

	public List<Priority> getAllPriority() {
		return priorityRepository.findAll();
	}

	public Priority getPeriorityById(Integer proorityId) {
		return priorityRepository.findById(proorityId).get();
	}
}
