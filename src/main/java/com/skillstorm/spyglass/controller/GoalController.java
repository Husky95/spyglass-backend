package com.skillstorm.spyglass.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.spyglass.model.Goals;
import com.skillstorm.spyglass.repository.GoalRepository;
import com.skillstorm.spyglass.service.GoalService;
import com.skillstorm.spyglass.user.UserEntity;
import com.skillstorm.spyglass.user.UserRepository;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController 
@CrossOrigin(origins = "*")
@RequestMapping(path="/goals")
public class GoalController {
	 @Autowired 
	 private GoalRepository goalRepository;
	 @Autowired
	 private GoalService goalService;
	 @Autowired
	 private  UserRepository userRepository;

	 /**
	   * Method handle GET request for getting all the Goal objects.
	   * @return All the Goal objects in database.
	   */
	 @GetMapping(path="/all/{username}")		
	  public @ResponseBody List getAllGoals(@PathVariable ("username") String username) {
	    return goalRepository.findAllByUsername(username);
		 //return goalRepository.findAll();
	  }
	 
	 /**
	   * Method handle POST request for creating a single Goal object.
	   * @param customer Goal object from front end.
	   * @return The created Goal object in database.
	   */
	 @PostMapping()
	 @ApiResponses(value = {
				@ApiResponse(responseCode = "201", description = "Successfully create a goal object and returned it mapping", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = Goals.class)) }),
				@ApiResponse(responseCode = "400", description = "Invalid Goal Object", content = @Content) })
	  public ResponseEntity<Goals> create(@Valid @RequestBody Goals newGoal) {
		System.out.println(newGoal);
		Goals returnGoal = goalRepository.save(newGoal);
		return new ResponseEntity<Goals>(returnGoal, HttpStatus.CREATED);

	  }
	 
	 
	 @PutMapping(path="/{id}")
	 @ApiResponses(value = {
				@ApiResponse(responseCode = "201", description = "Successfully update a goal object and returned it mapping", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = Goals.class)) }),
				@ApiResponse(responseCode = "400", description = "Invalid Goal Object", content = @Content) })
	 public ResponseEntity<Goals> update(@PathVariable  (value = "id") int id, @Valid @RequestBody Goals newGoal) {
			Goals goal = goalService.findById(id);
			Goals returnGoal = goalService.update(goal,newGoal);
			return new ResponseEntity<Goals>(returnGoal, HttpStatus.CREATED);

	 }
	 
	 @PutMapping(path="/deposit/{id}")
	 public Goals updateCurrentAmount(@PathVariable  (value = "id") int id, @Valid @RequestBody int currentAmount) {
			Goals goal = goalService.findById(id);
			goal.setCurrentAmount(currentAmount);
			return goalRepository.save(goal);
	 }
	 
	 /**
	   * Method handle DELETE request for deleting a Goal objects by goal_id.
	   * @param id gaol_id value from the front end.
	   * @return The deleted goal objects where goal_id in database equal to the pass in id.
	   */
	  @DeleteMapping(path="/{id}")
	  public void delete(@PathVariable  (value = "id") int id) {
		System.out.println("Delete call");
	  	goalRepository.deleteById(id);
	  }
	  
	 
}
