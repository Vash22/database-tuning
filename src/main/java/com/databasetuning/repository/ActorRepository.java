package com.databasetuning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.databasetuning.entity.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
