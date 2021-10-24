package com.databasetuning.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.databasetuning.dto.ActorDTO;
import com.databasetuning.entity.Actor;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Component
public class RunOnStartUp {

	private final PersistencePerformanceCheckService persistencePerformanceCheckService;

	@EventListener(ApplicationReadyEvent.class)
	public void persistAllData() throws FileNotFoundException {
		File file = ResourceUtils.getFile("classpath:db/data/actor500k.csv");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		List<ActorDTO> actorDTOs = new CsvToBeanBuilder(bufferedReader).withType(ActorDTO.class).build().parse();
		List<Actor> actors = actorDTOs.stream().map(ac -> new Actor(ac.getId(), ac.getFirstName(), ac.getLastName(), ac.getGender(), Set.of()))
				.collect(Collectors.toList());
		persistencePerformanceCheckService.persistWithoutTransactionBySave(actors);
	}
}