package com.databasetuning.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "directorId")
@Entity
public class DirectorGenre {

	@Id
	@SequenceGenerator(name = "director_genre_seq", sequenceName = "director_genre_seq")
	@GeneratedValue(generator = "director_genre_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	private long directorId;
	private String genre;
	private double prob;
}
