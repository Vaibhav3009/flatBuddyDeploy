package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "habits")
public class Habits {


	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getSuperClean() {
		return superClean;
	}
	public void setSuperClean(Boolean superClean) {
		this.superClean = superClean;
	}
	public Boolean getNightOwl() {
		return nightOwl;
	}
	public void setNightOwl(Boolean nightOwl) {
		this.nightOwl = nightOwl;
	}
	public Boolean getEarlyBird() {
		return earlyBird;
	}
	public void setEarlyBird(Boolean earlyBird) {
		this.earlyBird = earlyBird;
	}
	public Boolean getSmoking() {
		return smoking;
	}
	public void setSmoking(Boolean smoking) {
		this.smoking = smoking;
	}
	public Boolean getDrinking() {
		return drinking;
	}
	public void setDrinking(Boolean drinking) {
		this.drinking = drinking;
	}
	public Boolean getVegetarian() {
		return vegetarian;
	}
	public void setVegetarian(Boolean vegetarian) {
		this.vegetarian = vegetarian;
	}
	public Boolean getStudent() {
		return student;
	}
	public void setStudent(Boolean student) {
		this.student = student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

	private Boolean superClean=false;
	private Boolean nightOwl=false;
	private Boolean earlyBird=false;
	private Boolean smoking=false;
	private Boolean drinking=false;
	private Boolean vegetarian=false;
	private Boolean student=false;
	@OneToOne(mappedBy = "habits", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	    private User user;
	
	
	

}
