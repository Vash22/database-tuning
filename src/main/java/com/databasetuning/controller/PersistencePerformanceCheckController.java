package com.databasetuning.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("noTransactionBySave")
	public void persistWithoutTransactionBySave() throws FileNotFoundException {
		List<Actor> actors = csvReaderService.readAllFromCsv();
		persistencePerformanceCheckService.persistWithoutTransactionBySave(actors);
	}

	@PostMapping("inTransactionBySave")
	public void persistWithTransactionBySave() throws FileNotFoundException {
		List<Actor> actors = csvReaderService.readAllFromCsv();
		persistencePerformanceCheckService.persistWithTransactionBySave(actors);
	}

	@PostMapping("inTransactionBySaveAll")
	public void persistWithoutTransactionBySaveAll() throws FileNotFoundException {
		List<Actor> actors = csvReaderService.readAllFromCsv();
		persistencePerformanceCheckService.persistWithoutTransactionBySaveAll(actors);
	}
}
