package com.skillstorm.spyglass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.spyglass.model.Goals;
import com.skillstorm.spyglass.user.UserEntity;

@Repository
public interface GoalRepository extends JpaRepository<Goals, Integer> {
	@Query("SELECT g FROM Goals g WHERE g.username=?1")
	List<Goals> findAllByUsername(String username);
}
	