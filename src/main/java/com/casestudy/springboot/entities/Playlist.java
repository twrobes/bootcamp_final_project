package com.casestudy.springboot.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table
public class Playlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name", length=24, nullable=false, unique=false)
	private String name;
	
	@OneToMany(mappedBy = "playlist")
	private List<Musician> musician;
	
	@ManyToMany(targetEntity = Songs.class)
	@JoinTable
	private Set<Songs> songs = new HashSet<>();
	
	public Playlist() {
		super();
	}

	public Playlist(String name, List<Musician> musician) {
		super();
		this.name = name;
		this.musician = musician;
	}

	public Playlist(String name, List<Musician> musician, Set<Songs> songs) {
		super();
		this.name = name;
		this.musician = musician;
		this.songs = songs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Musician> getMusician() {
		return musician;
	}

	public void setMusician(List<Musician> musician) {
		this.musician = musician;
	}

	public Set<Songs> getSongs() {
		return songs;
	}

	public void setSongs(Set<Songs> songs) {
		this.songs = songs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((musician == null) ? 0 : musician.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((songs == null) ? 0 : songs.hashCode());
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
		Playlist other = (Playlist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (musician == null) {
			if (other.musician != null)
				return false;
		} else if (!musician.equals(other.musician))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (songs == null) {
			if (other.songs != null)
				return false;
		} else if (!songs.equals(other.songs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", name=" + name + ", musician=" + musician + ", songs=" + songs + "]";
	}
}