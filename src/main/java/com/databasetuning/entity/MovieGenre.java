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
@EqualsAndHashCode(of = "movieId")
@Entity
public class MovieGenre {

	@Id
	@SequenceGenerator(name = "movie_genre_seq", sequenceName = "movie_genre_seq")
	@GeneratedValue(generator = "movie_genre_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	private long movieId;
	private String genre;
}
