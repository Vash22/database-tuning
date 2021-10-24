package com.databasetuning.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Director {

	@Id
	@SequenceGenerator(name = "director_seq", sequenceName = "director_seq")
	@GeneratedValue(generator = "director_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	private String firstName;
	private String lastName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "directorId")
	private Set<DirectorGenre> directorGenres;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "movieDirector", joinColumns = { @JoinColumn(name = "directorId") }, inverseJoinColumns = { @JoinColumn(name = "movieId") })
	private Set<Movie> movies;

	public Director(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
