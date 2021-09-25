package model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "hobbies")

public class Hobbies {

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getMovies() {
		return movies;
	}
	public void setMovies(Boolean movies) {
		this.movies = movies;
	}
	public Boolean getGaming() {
		return gaming;
	}
	public void setGaming(Boolean gaming) {
		this.gaming = gaming;
	}
	public Boolean getSports() {
		return sports;
	}
	public void setSports(Boolean sports) {
		this.sports = sports;
	}
	public Boolean getReading() {
		return reading;
	}
	public void setReading(Boolean reading) {
		this.reading = reading;
	}
	public Boolean getWriting() {
		return writing;
	}
	public void setWriting(Boolean writing) {
		this.writing = writing;
	}
	public Boolean getPhotography() {
		return photography;
	}
	public void setPhotography(Boolean photography) {
		this.photography = photography;
	}
	public Boolean getHighVolumeMusic() {
		return highVolumeMusic;
	}
	public void setHighVolumeMusic(Boolean highVolumeMusic) {
		this.highVolumeMusic = highVolumeMusic;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @OneToOne(mappedBy = "hobbies", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private User user;

    private Boolean movies=false;
    private Boolean gaming=false;
    private Boolean sports=false;
    private Boolean reading=false;
    private Boolean writing=false;
    private Boolean photography=false;
    private Boolean highVolumeMusic=false;
}