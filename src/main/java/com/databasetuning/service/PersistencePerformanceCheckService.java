package com.databasetuning.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.databasetuning.entity.Actor;
import com.databasetuning.logging.InvocationTimeLogger;
import com.databasetuning.repository.ActorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@InvocationTimeLogger
public class PersistencePerformanceCheckService {

	private final ActorRepository actorRepository;

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void persistWithoutTransactionBySave(List<Actor> actors) {
		actors.forEach(actorRepository::save);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void persistWithTransactionBySaveAll(List<Actor> actors) {
		actorRepository.saveAll(actors);
	}
}
