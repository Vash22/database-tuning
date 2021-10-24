package com.databasetuning.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class RoleIdentity implements Serializable {

	private static final long serialVersionUID = 6025459355945818445L;

	private long actorId;
	private long movieId;
}
