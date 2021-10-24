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
public class Movie {

	@Id
	@SequenceGenerator(name = "movie_seq", sequenceName = "movie_seq")
	@GeneratedValue(generator = "movie_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private long year;
	private double rank;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "movieDirector", joinColumns = { @JoinColumn(name = "movieId") }, inverseJoinColumns = { @JoinColumn(name = "directorId") })
	private Set<Director> directors;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movieId")
	private Set<MovieGenre> movieGenres;
}
