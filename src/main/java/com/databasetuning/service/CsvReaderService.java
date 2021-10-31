package com.databasetuning.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.databasetuning.dto.ActorDTO;
import com.databasetuning.logging.InvocationTimeLogger;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class CsvReaderService {

	@InvocationTimeLogger
	public List<ActorDTO> readAllFromCsv() throws FileNotFoundException {
		File file = ResourceUtils.getFile("classpath:db/data/actor500k.csv");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		return new CsvToBeanBuilder<ActorDTO>(bufferedReader).withType(ActorDTO.class).build().parse();
	}
}
