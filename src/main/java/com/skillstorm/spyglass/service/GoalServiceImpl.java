package com.skillstorm.spyglass.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.spyglass.model.Goals;
import com.skillstorm.spyglass.repository.GoalRepository;

@Service
public class GoalServiceImpl  implements GoalService {

	@Autowired 
	private GoalRepository goalRepository;
	
	@Override
	public Goals findById(int id) {
		Optional<Goals> goal = goalRepository.findById(id);
		return goal.isPresent() ? goal.get() : null;		
	}
	
	@Override
	public Goals update(Goals goal, Goals newGoal) {
		goal.setName(newGoal.getName());
		goal.setDescription(newGoal.getDescription());
		goal.setTargetAmount(newGoal.getTargetAmount());
		goal.setCurrentAmount(newGoal.getCurrentAmount());
		goal.setTargetDate(newGoal.getTargetDate());
		goal.setImageSrc(newGoal.getImageSrc());
		return goalRepository.save(goal);
	}
	
}
