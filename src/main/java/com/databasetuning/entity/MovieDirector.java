package com.databasetuning.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "directorId", "movieId" })
@Entity
public class MovieDirector implements Serializable {

	private static final long serialVersionUID = -3765157576847051496L;

	@Id
	private long directorId;
	@Id
	private long movieId;
}
