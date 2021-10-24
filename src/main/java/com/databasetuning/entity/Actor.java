package com.databasetuning.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@AllArgsConstructor
public class Actor {

	@Id
	@SequenceGenerator(name = "actor_seq", sequenceName = "actor_seq")
	@GeneratedValue(generator = "actor_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	private String firstName;
	private String lastName;
	private String gender;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roleIdentity.actorId")
	private Set<Role> roles;

	public Actor(String firstName, String lastName, String gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}
}

