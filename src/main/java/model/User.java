package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

@Entity
@Table(name = "users")

public class User {

	@JsonManagedReference
	public Hobbies getHobbies() {
		return hobbies;
	}

	public void setHobbies(Hobbies hobbies) {
		this.hobbies = hobbies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hobbies_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Hobbies hobbies;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "habits_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Habits habits;

	private String username;


	private String hobbyList;

	private String habitList;


	@NotNull
	private String userId;

	private String name;

	private String email;

	private Integer budgetMin;

	private Integer budgetMax;

	private String location;

	@JsonManagedReference
	public Habits getHabits() {
		return habits;
	}

	public void setHabits(Habits habits) {
		this.habits = habits;
	}

	public String getHobbyList() {
		return hobbyList;
	}

	public void setHobbyList(String hobbyList) {
		this.hobbyList = hobbyList;
	}

	public String getHabitList() {
		return habitList;
	}

	public void setHabitList(String habitList) {
		this.habitList = habitList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getBudgetMin() {
		return budgetMin;
	}

	public void setBudgetMin(Integer budgetMin) {
		this.budgetMin = budgetMin;
	}

	public Integer getBudgetMax() {
		return budgetMax;
	}

	public void setBudgetMax(Integer budgetMax) {
		this.budgetMax = budgetMax;
	}
}
