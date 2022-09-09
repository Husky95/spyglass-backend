package com.skillstorm.spyglass.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skillstorm.spyglass.model.Goals;

@DataJpaTest

public class GoalsRepositoryTest {
	//@Autowired
	//private GoalRepository goalsRepository;
	@Test
	public void testUpdate() {
		Goals goal = new Goals();
		//goal = goalsRepository.save(goal);
		Goals newGoal = new Goals();
	
		goal.setName("Test");
		newGoal.setName("TEST");
		//Goals newGoal = goalsRepository.save(goal);
		
		assertEquals(goal.getName(), newGoal.getName()); // updated the image source
	}
}
