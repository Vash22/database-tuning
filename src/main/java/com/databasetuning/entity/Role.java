package com.databasetuning.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "roleIdentity")
@Entity
public class Role {

	@EmbeddedId
	private RoleIdentity roleIdentity;

	private String role;
}
