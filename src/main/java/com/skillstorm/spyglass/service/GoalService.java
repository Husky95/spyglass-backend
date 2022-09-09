package com.skillstorm.spyglass.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.skillstorm.spyglass.model.Goals;
@Service
public interface GoalService {
	Goals findById(int id);

	Goals update(Goals goal, @Valid Goals newGoal);
}
