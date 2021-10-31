package com.databasetuning.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.databasetuning.dto.ActorDTO;
import com.databasetuning.entity.Actor;
import com.databasetuning.service.CsvReaderService;
import com.databasetuning.service.PersistencePerformanceCheckService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("persistence")
public class PersistencePerformanceCheckController {

	private final PersistencePerformanceCheckService persistencePerformanceCheckService;
	private final CsvReaderService csvReaderService;

	@PostMapping("noTransaction")
	public void persistWithoutTransactionBySave() throws FileNotFoundException {
		List<Actor> actors = mapToActors(csvReaderService.readAllFromCsv());
		persistencePerformanceCheckService.persistWithoutTransactionBySave(actors);
	}

	@PostMapping("inTransaction")
	public void persistWithTransactionBySaveAll() throws FileNotFoundException {
		List<Actor> actors = mapToActors(csvReaderService.readAllFromCsv());
		persistencePerformanceCheckService.persistWithTransactionBySaveAll(actors);
	}

	@PostMapping("inTransactionWithSequence")
	public void persistWithSequenceAndTransactionBySaveAll() throws FileNotFoundException {
		List<Actor> actors = mapToActorsWithoutId(csvReaderService.readAllFromCsv());
		persistencePerformanceCheckService.persistWithTransactionBySaveAll(actors);
	}

	private List<Actor> mapToActors(List<ActorDTO> actorDTOs) {
		return actorDTOs.stream().map(ac -> new Actor(ac.getId(), ac.getFirstName(), ac.getLastName(), ac.getGender())).collect(Collectors.toList());
	}

	private List<Actor> mapToActorsWithoutId(List<ActorDTO> actorDTOs) {
		return actorDTOs.stream().map(ac -> new Actor(null, ac.getFirstName(), ac.getLastName(), ac.getGender())).collect(Collectors.toList());
	}
}
