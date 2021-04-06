package com.casestudy.springboot.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Musician {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="fName", length=24, nullable=false, unique=false)
	private String fName;
	
	@Column(name="lName", length=24, nullable=false, unique=false)
	private String lName;
	
	@Column(name="age", nullable=true, unique=false)
	private Integer age;
	
	@OneToOne(mappedBy = "musician")
	private User user;
	
	@OneToMany(targetEntity = Playlist.class, cascade=CascadeType.ALL)
	@JoinTable
	private List<Playlist> playlist = new ArrayList<>();
	
	public Musician() {
		super();
	}

	public Musician(String fName, String lName, Integer age, User user, List<Playlist> playlist) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.age = age;
		this.user = user;
		this.playlist = playlist;
	}

	public Musician(String fName, String lName, Integer age, User user) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.age = age;
		this.user = user;
	}

	public Musician(String fName, String lName, Integer age) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Playlist> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<Playlist> playlist) {
		this.playlist = playlist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + ((playlist == null) ? 0 : playlist.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Musician other = (Musician) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (playlist == null) {
			if (other.playlist != null)
				return false;
		} else if (!playlist.equals(other.playlist))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Musician [id=" + id + ", fName=" + fName + ", lName=" + lName + ", age=" + age + ", user=" + user
				+ ", playlist=" + playlist + "]";
	}
}
