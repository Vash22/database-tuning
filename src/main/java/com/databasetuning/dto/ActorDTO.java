package com.databasetuning.dto;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorDTO {

	@CsvBindByName
	private Long id;
	@CsvBindByName(column = "first_name")
	private String firstName;
	@CsvBindByName(column = "last_name")
	private String lastName;
	@CsvBindByName
	private String gender;
}
