package com.skillstorm.spyglass.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.skillstorm.spyglass.user.CurrentUser;
import com.skillstorm.spyglass.user.UserEntity;
	
@Entity(name="Goals") 
@Table(name="Goals")
public class Goals {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "goal_id")
	private int id;
	

    @Column(name = "name")
    @NotBlank	
    private String name;
    
    @Column(name = "description")
    @NotBlank	
	private String description;
    
    @Column(name = "target_amount")
    @NotNull
	private float targetAmount;
    
    @Column(name = "current_amount")
    @NotNull	
	private float currentAmount;
    
    @Column(name = "target_date")
    private LocalDate targetDate;
    
	@Column(name = "image_source")
    @NotBlank	
	private String imageSrc;

	@Column(name = "username")
    @NotBlank	
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIdentityReference(alwaysAsId = true)
	private UserEntity user;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	
	@Override
	public String toString() {
		return "Goals [id=" + id + ", name=" + name + ", description=" + description + ", targetAmount=" + targetAmount
				+ ", currentAmount=" + currentAmount + ", imgSrc=" + imageSrc + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(float targetAmount) {
		this.targetAmount = targetAmount;
	}

	public float getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(float currentAmount) {
		this.currentAmount = currentAmount;
	}

	
	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
}
